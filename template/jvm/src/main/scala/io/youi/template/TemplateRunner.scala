package io.youi.template

import java.io.File

import org.rogach.scallop._
import scribe.{LogHandler, Logger}
import scribe.formatter.FormatterBuilder

import scala.io.StdIn

object TemplateRunner {
  def main(args: Array[String]): Unit = {
    Logger.root.clearHandlers()
    Logger.root.addHandler(LogHandler(formatter = FormatterBuilder().date().string(" - ").message.newLine))

    val conf = new Conf(args)
    val mode = conf.mode.getOrElse("compile")
    val optimize = conf.modification.getOrElse("none") == "optimize"
    val source = new File(conf.source())
    val destination = new File(conf.destination())
    val host = conf.host.getOrElse("0.0.0.0")
    val port = conf.port.getOrElse(8080)
    assert(source.isDirectory, s"Source directory must be a directory (${source.getAbsolutePath})")
    assert(!destination.isFile, s"Destination must be a directory, but found a file (${destination.getAbsolutePath})")
    destination.mkdirs()

    val compiler = new TemplateCompiler(source, destination, removeDotHTML = true, consoleCommands = true, optimize = optimize)
    try {
      compiler.compileAll(deleteFirst = true)
      if (mode.equalsIgnoreCase("watch") || mode.equalsIgnoreCase("server")) {
        compiler.watch()
      }
      if (mode.equalsIgnoreCase("server")) {
        compiler.startServer(host, port)
      }
      if (mode.equalsIgnoreCase("watch") || mode.equalsIgnoreCase("server")) {
        println("Press ENTER on your keyboard to stop...")
        StdIn.readLine()
        println("Shutting down...")
        compiler.stopWatching()
        if (mode.equalsIgnoreCase("server")) {
          compiler.stopServer()
        }
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

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val mode: ScallopOption[String] = opt[String](required = false)
  val modification: ScallopOption[String] = opt[String](required = false)
  val source: ScallopOption[String] = opt[String](required = true)
  val destination: ScallopOption[String] = opt[String](required = true)
  val host: ScallopOption[String] = opt[String](required = false)
  val port: ScallopOption[Int] = opt[Int](required = false)
  verify()
}