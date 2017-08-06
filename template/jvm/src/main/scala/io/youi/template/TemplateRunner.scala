package io.youi.template

import java.io.File

import profig.{Config, ConfigApplication}
import scribe.{LogHandler, Logger}
import scribe.formatter.FormatterBuilder

import scala.io.StdIn

object TemplateRunner extends ConfigApplication {
  override def main(args: Array[String]): Unit = start(args)

  override protected def run(): Unit = {
    Logger.root.clearHandlers()
    Logger.root.addHandler(LogHandler(formatter = FormatterBuilder().date().string(" - ").message.newLine))
    val config = Config.as[TemplateConfig]
    assert(config.source.nonEmpty, "Source path must be specified (-source).")
    val source = new File(config.source.get)
    assert(source.isDirectory, s"Source directory must be a directory (${source.getAbsolutePath})")
    assert(config.destination.nonEmpty, "Destination path must be specified (-destination).")
    val destination = new File(config.destination.get)
    assert(!destination.isFile, s"Destination must be a directory, but found a file (${destination.getAbsolutePath})")
    destination.mkdirs()
    val optimize = config.modification == "optimize"

    val compiler = new TemplateCompiler(source, destination, removeDotHTML = true, consoleCommands = true, optimize = optimize)
    try {
      compiler.compileAll(deleteFirst = true)
      if (config.mode.equalsIgnoreCase("watch") || config.mode.equalsIgnoreCase("server")) {
        compiler.watch()
      }
      if (config.mode.equalsIgnoreCase("server")) {
        compiler.startServer()
      }
      if (config.mode.equalsIgnoreCase("watch") || config.mode.equalsIgnoreCase("server")) {
        println("Press ENTER on your keyboard to stop...")
        StdIn.readLine()
        println("Shutting down...")
        compiler.stopWatching()
        if (config.mode.equalsIgnoreCase("server")) {
          compiler.stopServer()
        }
      } else {
        System.exit(0)
      }
    } catch {
      case t: Throwable => {
        scribe.error(t)
        compiler.stopWatching()
        compiler.stopServer()
        System.exit(0)
      }
    }
  }
}

case class TemplateConfig(mode: String = "compile",
                          modification: String = "none",
                          source: Option[String],
                          destination: Option[String])