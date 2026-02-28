package youi.optimizer

import java.io.{File, FileNotFoundException}
import java.net.{URI, URL}
import com.roundeights.hasher.Hasher
import spice.delta.{HTMLParser, Tag}
import spice.delta.types.Delta
import spice.streamer.Streamer
import spice.streamer.{file2Writer, string2Reader, url2Reader}

import scala.collection.mutable.ListBuffer

object HTMLOptimizer {
  import spice.delta.Selector._

  private def createTempFile(fileType: String, extension: String): File = {
    val temp = File.createTempFile(s"youi-optimizer-$fileType", s".$extension")
    temp.deleteOnExit()
    temp
  }

  /**
    * Cleans up the HTML removing all JavaScript (inline and external) creating files for them.
    *
    * @param input the original HTML file to be optimized
    * @param baseDirectory the base directory to look up JavaScript paths from
    * @return Optimized
    */
  private def stage1(input: File, baseDirectory: File): Optimized = {
    val stream = HTMLParser(input)
    var scripts = ListBuffer.empty[ScriptFile]
    val result = stream.stream(List(
      Delta.Process(ByTag("script"), replace = true, onlyOpenTag = false, processor = (openTag: Tag.Open, content: String) => {
        val script: ScriptFile = openTag.attributes.get("src") match {
          case Some(src) => {           // External script file
            val minified = src.toLowerCase.endsWith(".min.js")
            if (src.startsWith("http://") || src.startsWith("https://") || src.startsWith("//")) {    // Remote script
              val file = createTempFile("remote", "js")
              val url = URI.create(src).toURL()
              Streamer(url, file).sync()
              val map = if (minified) {
                try {
                  val minifiedFile = createTempFile("remote", "js.map")
                  val minifiedURL = URI.create(s"$src.map").toURL()
                  Streamer(minifiedURL, minifiedFile).sync()
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
                val minifiedFile = new File(baseDirectory, s"$src.map")
                if (minifiedFile.isFile) {
                  Some(minifiedFile)
                } else {
                  scribe.warn(s"No map file found for ${minifiedFile.getAbsolutePath}")
                  None
                }
              } else {
                None
              }
              ScriptFile(new File(baseDirectory, src), map)
            }
          }
          case None => {                // Inline script
            val close = openTag.close.get
            val start = openTag.end - openTag.start
            val end = close.start - openTag.end
            val script = content.substring(start, start + end).trim
            val file = createTempFile("inline", "js")
            file.deleteOnExit()
            Streamer(script, file).sync()
            ScriptFile(file, None)
          }
        }
        scripts += script
        ""
      })
    ))

    val output = createTempFile("stage1", "html")
    Streamer(result, output).sync()
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
    Streamer(content, htmlFileOut).sync()
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

  private def nextJSFile(directory: File, jsPath: String, increment: Int = 0): File = {
    val name = jsPath.substring(0, jsPath.lastIndexOf('.'))
    val fileName = if (increment == 0) {
      s"$name.js"
    } else {
      s"$name$increment.js"
    }
    val file = new File(directory, fileName)
    if (!file.exists()) {
      file
    } else {
      nextJSFile(directory, jsPath, increment + 1)
    }
  }

  private var cache = Map.empty[String, CachedOptimization]

  def optimize(baseDirectory: File, directory: File, htmlPath: String, jsPath: String): Unit = synchronized {
    try {
      scribe.info(s"Optimizing $htmlPath...")
      val html = new File(directory, htmlPath)
      val optimized = stage1(html, baseDirectory)
      if (optimized.scripts.isEmpty) {
        scribe.info(s"No JavaScript in $htmlPath, skipping...")
      } else {
        val cached = cache.get(optimized.crc32) match {
          case Some(c) => {
            scribe.info(s"Using cached JavaScript file: ${c.js.getAbsolutePath}")
            c
          }
          case None => {
            val jsFile = nextJSFile(baseDirectory, jsPath)
            scribe.info(s"Creating new JavaScript file: ${jsFile.getAbsolutePath}")
            stage2(optimized)
            stage3(optimized, jsFile)
            CachedOptimization(optimized.scripts, jsFile, optimized.crc32)
          }
        }
        val js = cached.js
        val updatedJSPath = js.getCanonicalPath.substring(directory.getCanonicalPath.length + (if (jsPath.startsWith("/")) 0 else 1))
        stage4(optimized.html, html, updatedJSPath)
        stage5(optimized, html, js)
        cached.usedBy += htmlPath
        cache += optimized.crc32 -> cached
      }
    } catch {
      case t: Throwable => throw new RuntimeException(s"Error occurred while processing $htmlPath", t)
    }
  }

  def outputStats(): Unit = {
    cache.values.foreach { cache =>
      scribe.info(s"${cache.js.getName} used by ${cache.usedBy.size} pages: ${cache.usedBy.mkString(", ")}")
    }
  }

  final def optimizeDirectory(directory: File, jsPath: String, recursive: Boolean = true, baseDirectory: Option[File] = None): Unit = {
    directory.listFiles().foreach { file =>
      if (file.getName.toLowerCase.endsWith(".html")) {       // Found HTML file
        optimize(baseDirectory.getOrElse(directory), file.getParentFile, file.getName, jsPath)
      } else if (file.isDirectory && recursive) {             // Found directory
        optimizeDirectory(file, jsPath, recursive, baseDirectory.orElse(Some(directory)))
      }
    }
    scribe.info(s"Processing completed. Processed ${cache.values.flatMap(_.usedBy).size} and created ${cache.size} optimized JavaScript files:")
    outputStats()
  }

  def dispose(): Unit = cache = Map.empty
}

case class Optimized(html: File, scripts: List[ScriptFile], originalSize: Long) {
  lazy val crc32: String = scripts.map(sf => Hasher(sf.js).crc32.hex).mkString(",")
}

case class CachedOptimization(scripts: List[ScriptFile], js: File, crc32: String, var usedBy: Set[String] = Set.empty)

case class ScriptFile(js: File, map: Option[File])