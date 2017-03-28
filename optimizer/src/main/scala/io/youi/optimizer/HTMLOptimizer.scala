package io.youi.optimizer

import java.io.{File, FileNotFoundException}
import java.net.URL

import io.youi.stream._
import org.powerscala.io._

import scala.collection.mutable.ListBuffer

object HTMLOptimizer {
  private def createTempFile(fileType: String, extension: String): File = {
    val temp = File.createTempFile(s"youi-optimizer-$fileType", s".$extension")
    temp.deleteOnExit()
    temp
  }

  /**
    * Cleans up the HTML removing all JavaScript (inline and external) creating files for them.
    *
    * @param input the original HTML file to be optimized
    * @return Optimized
    */
  private def stage1(input: File): Optimized = {
    val directory = input.getParentFile
    val stream = HTMLParser(input)
    var scripts = ListBuffer.empty[ScriptFile]
    val result = stream.stream(List(
      Delta.Process(ByTag("script"), replace = true, onlyOpenTag = false, processor = (openTag: OpenTag, content: String) => {
        val script: ScriptFile = openTag.attributes.get("src") match {
          case Some(src) => {           // External script file
            val minified = src.toLowerCase.endsWith(".min.js")
            if (src.startsWith("http://") || src.startsWith("https://") || src.startsWith("//")) {    // Remote script
              val file = createTempFile("remote", "js")
              val url = new URL(src)
              IO.stream(url, file)
              val map = if (minified) {
                try {
                  val minifiedFile = createTempFile("remote", "js.map")
                  val minifiedURL = new URL(s"$src.map")
                  IO.stream(minifiedURL, minifiedFile)
                  Some(minifiedFile)
                } catch {
                  case exc: FileNotFoundException => {
                    scribe.warn(s"No map file found for $src.map")
                    None
                  }
                }
              } else {
                None
              }
              ScriptFile(file, map)
            } else {                                                                                  // Local script
              val map = if (minified) {
                val minifiedFile = new File(directory, s"$src.map")
                if (minifiedFile.isFile) {
                  Some(minifiedFile)
                } else {
                  scribe.warn(s"No map file found for ${minifiedFile.getAbsolutePath}")
                  None
                }
              } else {
                None
              }
              ScriptFile(new File(directory, src), map)
            }
          }
          case None => {                // Inline script
            val close = openTag.close.get
            val start = openTag.end - openTag.start
            val end = close.start - openTag.end
            val script = content.substring(start, start + end).trim
            val file = createTempFile("inline", "js")
            file.deleteOnExit()
            IO.stream(script, file)
            ScriptFile(file, None)
          }
        }
        scripts += script
        ""
      })
    ))

    val output = createTempFile("stage1", "html")
    IO.stream(result, output)
    Optimized(output, scripts.toList, input.length())
  }

  /**
    * Verified all the JavaScript and Map files exist.
    *
    * @param optimized the result from stage1
    */
  private def stage2(optimized: Optimized): Unit = {
    optimized.scripts.foreach { scriptFile =>
      assert(scriptFile.js.isFile, s"Script file referenced does not exist: ${scriptFile.js.getAbsolutePath}.")
      scriptFile.map.foreach(f => s"SourceMap file referenced does not exist: ${f.getAbsolutePath} for ${scriptFile.js.getAbsolutePath}.")
    }
  }

  /**
    * Optimize all JavaScript files into a single JS file
    *
    * @param optimized the result from stage1
    * @param output the optimized JavaScript file
    */
  private def stage3(optimized: Optimized, output: File): Unit = {
    JavaScriptOptimizer.optimize(optimized.scripts, output)
  }

  /**
    * Re-injects a single script reference t
    *
    * @param htmlFileIn the HTML file to modify
    * @param htmlFileOut the resulting HTML file
    * @param jsPath the path of the JavaScript file to inject
    */
  private def stage4(htmlFileIn: File, htmlFileOut: File, jsPath: String): Unit = {
    val stream = HTMLParser(htmlFileIn)
    val content = stream.stream(List(
      Delta.InsertLastChild(ByTag("body"), s"""<script src="$jsPath"></script>""")
    ))
    IO.stream(content, htmlFileOut)
  }

  /**
    * Outputs information about the amount of space saved by optimization.
    *
    * @param optimized the result from stage1
    * @param htmlFile the htmlFileOut from stage4
    * @param jsFile the resulting JavaScript file specified in stage3
    */
  private def stage5(optimized: Optimized, htmlFile: File, jsFile: File): Unit = {
    val htmlTrimmed = optimized.originalSize - htmlFile.length()
    val originalJavaScript = optimized.scripts.map(_.js.length()).sum
    val jsTrimmed = originalJavaScript - jsFile.length()
    scribe.info(s"Saved(HTML: $htmlTrimmed, JavaScript: $jsTrimmed, Merged: ${optimized.scripts.size} JavaScript files)")
  }

  def optimize(directory: File, htmlPath: String, jsPath: String): Unit = {
    // TODO: Support incrementing js name if duplicates are found
    val html = new File(directory, htmlPath)
    val js = new File(directory, jsPath)
    val optimized = stage1(html)
    // TODO: detect if these scripts have been optimized already
    stage2(optimized)
    stage3(optimized, js)
    stage4(optimized.html, html, jsPath)
    stage5(optimized, html, js)
    // TODO: cache optimized for multiple runs
  }
}

case class Optimized(html: File, scripts: List[ScriptFile], originalSize: Long)

case class ScriptFile(js: File, map: Option[File])