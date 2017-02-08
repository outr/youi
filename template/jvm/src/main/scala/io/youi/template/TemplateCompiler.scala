package io.youi.template

import java.io.File

import com.outr.scribe._
import io.youi.Priority
import io.youi.http.FileContent
import io.youi.net.ContentType
import io.youi.server.UndertowServer
import io.youi.server.handler.CachingManager
import io.youi.stream.{ByTag, Delta, HTMLParser}
import org.powerscala.io._

import scala.collection.mutable.ListBuffer
import scala.sys.process._

class TemplateCompiler(sourceDirectory: File,
                       destinationDirectory: File,
                       compressCSS: Boolean = false,
                       removeDotHTML: Boolean = false) {
  private val server = new UndertowServer {
    handler.caching(CachingManager.NotCached).file(destinationDirectory)
    handler.priority(Priority.Low).handle { httpConnection =>
      httpConnection.response.content match {
        case Some(content) => content match {
          case FileContent(file, contentType) => if (contentType == ContentType.`application/octet-stream` && file.getName.indexOf('.') == -1) {
            httpConnection.update { response =>
              response.withContent(FileContent(file, ContentType.`text/html`))
            }
          }
          case _ =>
        }
        case None =>
      }
    }
    // TODO: inject Scala.js into HTML files
  }

  private val watcher = new Watcher(sourceDirectory.toPath, eventDelay = 3000L) {
    override def fire(pathEvent: PathEvent): Unit = {
      val pathType = pathEvent.directory.toString.substring(sourceDirectory.getAbsolutePath.length + 1) match {
        case s if s.indexOf('/') != -1 => s.substring(0, s.indexOf('/'))
        case s => s
      }

      pathType match {
        case "pages" => {
          logger.info(s"Compiling page: ${pathEvent.path}")
          compilePage(pathEvent.path.toFile)
        }
        // TODO: support explicit rebuilding for less, sass, and parts
        case _ => {
          logger.info(s"Modification triggering rebuild: ${pathEvent.path}")
        }
      }
    }
  }

  def watch(): Unit = watcher.start()

  def stopWatching(): Unit = watcher.dispose()

  def startServer(): Unit = server.start()

  def stopServer(): Unit = server.stop()

  def compileAll(deleteFirst: Boolean): Unit = {
    // Delete output
    if (deleteFirst) {
      deleteDestination()
    }

    // Generate pages
    val pagesDirectory = new File(sourceDirectory, "pages")
    pagesDirectory.listFiles.foreach {
      case f if f.getName.endsWith(".html") => compilePage(f)
      case _ => // Ignore non-html files
    }

    // Copy assets
    copyAssets()

    // Compile LESS
    val lessDirectory = new File(sourceDirectory, "less")
    if (lessDirectory.exists()) {
      lessDirectory.listFiles().foreach {
        case f if f.isFile && f.getName.endsWith(".less") => {
          compileLess(f.getName, compressCSS)
        }
        case _ => // Ignore others
      }
    }

    // Compile SASS files
    val sassDirectory = new File(sourceDirectory, "sass")
    if (sassDirectory.exists()) {
      sassDirectory.listFiles().foreach {
        case f if f.isFile && (f.getName.endsWith(".sass") || f.getName.endsWith(".scss")) && !f.getName.startsWith("_") => {
          compileSass(f.getName, compressCSS)
        }
        case _ => // Ignore others
      }
    }
  }

  def compilePage(fileName: String): Unit = {
    val source = new File(sourceDirectory, s"pages/$fileName")
    compilePage(source)
  }

  def compilePage(source: File): Unit = {
    val fileName = source.getName
    val destinationFileName = if (removeDotHTML) {
      fileName.substring(0, fileName.lastIndexOf('.'))
    } else {
      fileName
    }
    val destination = new File(destinationDirectory, destinationFileName)
    val html = compileHTML(source).replaceAll("""\$\{.*?\}""", "")
    destination.getParentFile.mkdirs()
    IO.stream(html, destination)
  }

  def compilePartial(filePath: String): String = {
    val source = new File(sourceDirectory, s"partials/$filePath")
    compileHTML(source)
  }

  def compileHTML(source: File): String = {
    val stream = HTMLParser.cache(source)
    val deltas = List(
      // Include support
      Delta.Process(ByTag("include"), replace = true, onlyOpenTag = true, (openTag, content) => {
        val src = openTag.attributes("src")
        if (openTag.close.nonEmpty) {
          val html = compilePartial(src)
          html.substring(0, html.indexOf("<content/>"))
        } else {
          compilePartial(src)
        }
      }, Some((openTag, closeTag, content) => {
        val src = openTag.attributes("src")
        val html = compilePartial(src)
        html.substring(html.indexOf("<content/>") + 10)
      }))
    )
    stream.stream(deltas)
  }

  def copyAssets(): Unit = {
    val assets = new File(sourceDirectory, "assets")
    if (assets.exists()) {
      IO.copy(assets, destinationDirectory)
    }
  }

  def deleteDestination(): Unit = {
    IO.delete(destinationDirectory)
  }

  def compileLess(filePath: String, compress: Boolean): Unit = {
    val input = new File(sourceDirectory, s"less/$filePath")
    val output = new File(destinationDirectory, s"css/${input.getName.substring(0, input.getName.lastIndexOf('.'))}.css")
    logger.info(s"Compiling LESS file ${input.getName}...")
    val command = new File(sourceDirectory.getParentFile, "node_modules/less/bin/lessc").getAbsolutePath
    val b = ListBuffer.empty[String]
    b += command
    if (compress) {
      b += "--compress"
    }
    b += input.getAbsolutePath
    b += output.getAbsolutePath
    val exitCode = b ! LoggingProcessLogger
    if (exitCode != 0) {
      throw new RuntimeException(s"Failed to compile LESS code!")
    }
  }

  def compileSass(filePath: String, compress: Boolean): Unit = {
    val input = new File(sourceDirectory, s"sass/$filePath")
    val output = new File(destinationDirectory, s"css/${input.getName.substring(0, input.getName.lastIndexOf('.'))}.css")

    logger.info(s"Compiling SASS file ${input.getName}...")
    val command = new File(sourceDirectory.getParentFile, "node_modules/node-sass/bin/node-sass").getAbsolutePath
    val b = ListBuffer.empty[String]
    b += command
    if (compress) {
      b += "--style compressed"
    }
    b += input.getAbsolutePath
    b += output.getAbsolutePath
    val exitCode = b ! LoggingProcessLogger
    if (exitCode != 0) {
      throw new RuntimeException(s"Failed to compile SASS code!")
    }
  }
}

object LoggingProcessLogger extends ProcessLogger {
  override def out(s: => String): Unit = System.out.println(s)

  override def err(s: => String): Unit = System.err.println(s)

  override def buffer[T](f: => T): T = f
}