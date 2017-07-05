package io.youi.template

import java.io.File

import io.youi.optimizer.HTMLOptimizer
import scribe.formatter.FormatterBuilder
import scribe.{LogHandler, Logger}
import io.youi.stream.{ByTag, Delta, HTMLParser}
import org.powerscala.io._
import org.powerscala.io.watcher.{PathEvent, Watcher}

import scala.collection.mutable.ListBuffer
import scala.io.StdIn
import scala.sys.process._

class TemplateCompiler(val sourceDirectory: File,
                       val destinationDirectory: File,
                       val compressCSS: Boolean = false,
                       val removeDotHTML: Boolean = false,
                       val consoleCommands: Boolean = true,
                       val optimize: Boolean = false) {
  private[template] var pages = Set.empty[String]

  private val server = new ServerTemplateApplication(this)

  private val watcher = new Watcher(sourceDirectory.toPath, eventDelay = 3000L) {
    override def fire(pathEvent: PathEvent): Unit = try {
      val file = pathEvent.path.toAbsolutePath.toFile
      val path = file.getCanonicalPath.substring(sourceDirectory.getCanonicalPath.length)
      if (path.startsWith("/pages") && path.endsWith(".html")) {
        scribe.info(s"Page changed (${pathEvent.path}), recompiling...")
        compilePage(pathEvent.path.toFile)
      } else if (path.startsWith("/less") && path.endsWith(".less")) {
        scribe.info(s"LESS file changed (${file.getName}), recompiling all LESS files...")
        compileAllLess()
      } else if (path.startsWith("/sass") && (path.endsWith(".sass") || path.endsWith(".scss"))) {
        scribe.info(s"SASS file changed (${file.getName}), recompiling all SASS files...")
        compileAllSass()
      } else if (path.startsWith("/partials")) {
        scribe.info(s"Partial page changed (${file.getName}), recompiling all pages...")
        compileAllPages()
      } else {
        scribe.info(s"Unknown path: $path, recompiling everything...")
        compileAll(deleteFirst = false)
      }

      // Reload all active pages
      server.communication.instances().foreach(_.reload(force = true))
    } catch {
      case t: Throwable => scribe.error(t)
    }
  }

  def watch(): Unit = watcher.start()

  def stopWatching(): Unit = watcher.dispose()

  def startServer(): Unit = {
    server.start()
  }

  def stopServer(): Unit = server.stop()

  def compileAll(deleteFirst: Boolean): Unit = {
    // Delete output
    if (deleteFirst) {
      deleteDestination()
    }

    // Copy assets
    copyAssets()

    // Compile LESS
    compileAllLess()

    // Compile SASS files
    compileAllSass()

    // Generate pages
    compileAllPages()
  }

  private def processRecursively(directory: File)(handler: File => Unit): Unit = directory.listFiles.foreach { file =>
    if (file.isDirectory) {
      processRecursively(file)(handler)
    } else {
      handler(file)
    }
  }

  def compileAllPages(): Unit = {
    val pagesDirectory = new File(sourceDirectory, "pages")
    processRecursively(pagesDirectory) { file =>
      if (file.getName.endsWith(".html")) {
        compilePage(file)
      }
    }
  }

  def compilePage(fileName: String): Unit = {
    val source = new File(sourceDirectory, s"pages/$fileName")
    compilePage(source)
  }

  def compilePage(source: File): Unit = {
    val fileName = source.getAbsolutePath match {
      case s => s.substring(s.indexOf("pages/") + 6)
    }
    val destination = new File(destinationDirectory, fileName)
    val html = compileHTML(source).replaceAll("""\$\{.*?\}""", "")
    destination.getParentFile.mkdirs()
    IO.stream(html, destination)

    if (optimize) {
      HTMLOptimizer.optimize(destinationDirectory, destinationDirectory, fileName, "/js/optimized.js")
    }

    // TODO: support CSS merging
    // TODO: support HTML minification

    synchronized {
      pages += fileName
    }
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

  def compileAllLess(): Unit = {
    val lessDirectory = new File(sourceDirectory, "less")
    if (lessDirectory.exists()) {
      lessDirectory.listFiles().foreach {
        case f if f.isFile && f.getName.endsWith(".less") => {
          compileLess(f.getName, compressCSS)
        }
        case _ => // Ignore others
      }
    }
  }

  def compileLess(filePath: String, compress: Boolean): Unit = {
    val input = new File(sourceDirectory, s"less/$filePath")
    val output = new File(destinationDirectory, s"css/${input.getName.substring(0, input.getName.lastIndexOf('.'))}.css")
    scribe.info(s"Compiling LESS file ${input.getName}...")
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

  def compileAllSass(): Unit = {
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

  def compileSass(filePath: String, compress: Boolean): Unit = {
    val input = new File(sourceDirectory, s"sass/$filePath")
    val output = new File(destinationDirectory, s"css/${input.getName.substring(0, input.getName.lastIndexOf('.'))}.css")

    scribe.info(s"Compiling SASS file ${input.getName}...")
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
//
//object TemplateCompiler {
//  def main(args: Array[String]): Unit = if (args.length == 4) {
//    Logger.root.clearHandlers()
//    Logger.root.addHandler(LogHandler(formatter = FormatterBuilder().date().string(" - ").message.newLine))
//
//    val mode = args(0)
//    val modification = args(1)
//    val sourceDirectory = new File(args(2))
//    val destinationDirectory = new File(args(3))
//
//    val optimize = modification == "optimize"
//
//    assert(sourceDirectory.isDirectory, s"Source directory must be a directory (${sourceDirectory.getAbsolutePath})")
//    assert(!destinationDirectory.isFile, s"Destination must be a directory, but found a file (${destinationDirectory.getAbsolutePath})")
//    destinationDirectory.mkdirs()
//
//    val compiler = new TemplateCompiler(sourceDirectory, destinationDirectory, removeDotHTML = true, consoleCommands = true, optimize = optimize)
//    try {
//      compiler.compileAll(deleteFirst = true)
//      if (mode.equalsIgnoreCase("watch") || mode.equalsIgnoreCase("server")) {
//        compiler.watch()
//      }
//      if (mode.equalsIgnoreCase("server")) {
//        compiler.startServer()
//      }
//      if (mode.equalsIgnoreCase("watch") || mode.equalsIgnoreCase("server")) {
//        println("Press ENTER on your keyboard to stop...")
//        StdIn.readLine()
//        println("Shutting down...")
//        compiler.stopWatching()
//        if (mode.equalsIgnoreCase("server")) {
//          compiler.stopServer()
//        }
//      }
//    } catch {
//      case t: Throwable => {
//        scribe.error(t)
//        compiler.stopWatching()
//        compiler.stopServer()
//        System.exit(0)
//      }
//    }
//  } else {
//    println("Usage: youi-template <mode> <modification> <source directory> <destination directory>")
//    println("\t<mode> is one of the following options:")
//    println("\t\tcompile - does a full compile and then exit")
//    println("\t\twatch - does a full compile, then watches for changes and compiles on-demand")
//    println("\t\tserver - does a full compile, then starts a server to serve the pages and will auto-reload the page on change")
//    println("\t<modification> defines modifications that should take place against the compiled template and must be one of the following options:")
//    println("\t\tnone - no optimizations or modifications are applied after compilation")
//    println("\t\toptimize - merges all JavaScript into a single source file (including inline and remote scripts), optimizes it, minifies it, and obfuscates it while generating a js.map file for it")
//    println("\t<source directory> is the location where the source files are stored. Supports the following sub-folders:")
//    println("\t\tassets - files within this directory or copied as-is into the destination directory")
//    println("\t\tless - looks for .less files to compile and put into the css directory of the destination")
//    println("\t\tpages - compiles and copies into the destination")
//    println("\t\tpartials - used during compilation of pages for includes")
//    println("\t\tsass - looks for .sass and .scss files to compile and put into the css directory of the destination")
//    println("\t<destination directory> is the location where the template is compiled to. Note: This directory will be completely deleted during compilation.")
//  }
//}