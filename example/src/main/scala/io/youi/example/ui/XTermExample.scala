package io.youi.example.ui

import rapid.Task
import io.youi.example.screen.UIExampleScreen
import io.youi.xterm.{XTermLoader, XTermView}
import reactify.stateful2Value
import spice.net._

import scala.scalajs.js

class XTermExample extends UIExampleScreen {
  override def title: String = "XTerm"
  override def path: URLPath = path"/examples/xterm.html"

  override def createUI(): Task[Unit] = XTermLoader.load().map { _ =>
    val term = new XTermView
    term.size.width := container.size.width
    term.size.height := container.size.height
    container.children += term
    term.initialize()

    writeBanner(term)
    writePrompt(term)

    var inputBuffer = ""

    term.onData.attach { data =>
      data match {
        case "\r" => // Enter
          term.write("\r\n")
          processCommand(term, inputBuffer.trim)
          inputBuffer = ""
        case "\u007f" => // Backspace
          if (inputBuffer.nonEmpty) {
            inputBuffer = inputBuffer.dropRight(1)
            term.write("\b \b")
          }
        case "\u0003" => // Ctrl+C
          term.write("^C\r\n")
          inputBuffer = ""
          writePrompt(term)
        case s =>
          inputBuffer += s
          term.write(s)
      }
    }
  }

  private def writeBanner(term: XTermView): Unit = {
    term.writeln("\u001b[1;36m╔══════════════════════════════════════╗\u001b[0m")
    term.writeln("\u001b[1;36m║\u001b[0m  \u001b[1;33mYouI Terminal\u001b[0m                       \u001b[1;36m║\u001b[0m")
    term.writeln("\u001b[1;36m║\u001b[0m  \u001b[0;37mType 'help' for available commands\u001b[0m  \u001b[1;36m║\u001b[0m")
    term.writeln("\u001b[1;36m╚══════════════════════════════════════╝\u001b[0m")
    term.writeln("")
  }

  private def writePrompt(term: XTermView): Unit = {
    term.write("\u001b[1;32myoui\u001b[0m\u001b[1;33m>\u001b[0m ")
  }

  private def processCommand(term: XTermView, command: String): Unit = {
    if (command.isEmpty) {
      writePrompt(term)
      return
    }

    val parts = command.split("\\s+", 2)
    val cmd = parts(0).toLowerCase
    val args = if (parts.length > 1) parts(1) else ""

    val response = cmd match {
      case "help" =>
        "\u001b[1;33mAvailable commands:\u001b[0m\r\n" +
        "  \u001b[1;32mhelp\u001b[0m       Show this help message\r\n" +
        "  \u001b[1;32mecho\u001b[0m       Echo text back (usage: echo <text>)\r\n" +
        "  \u001b[1;32mdate\u001b[0m       Show the current date and time\r\n" +
        "  \u001b[1;32mclear\u001b[0m      Clear the terminal\r\n" +
        "  \u001b[1;32mhello\u001b[0m      A friendly greeting\r\n" +
        "  \u001b[1;32mabout\u001b[0m      About YouI Terminal\r\n" +
        "  \u001b[1;32mcolors\u001b[0m     Show ANSI color palette"
      case "echo" =>
        if (args.nonEmpty) args else "Usage: echo <text>"
      case "date" =>
        new js.Date().toString()
      case "clear" =>
        term.clearTerminal()
        writePrompt(term)
        return
      case "hello" =>
        "\u001b[1;35mHello from YouI! \u001b[0m\u001b[0;37mWelcome to the terminal.\u001b[0m"
      case "about" =>
        "\u001b[1;36mYouI Terminal\u001b[0m\r\n" +
        "A terminal emulator powered by xterm.js\r\n" +
        "Built with the YouI UI framework for Scala.js"
      case "colors" =>
        val normal = (0 until 8).map(i => s"\u001b[4${i}m  \u001b[0m").mkString
        val bright = (0 until 8).map(i => s"\u001b[10${i}m  \u001b[0m").mkString
        s"Normal:  $normal\r\nBright:  $bright"
      case _ =>
        s"\u001b[1;31mUnknown command:\u001b[0m $command\r\nType '\u001b[1;32mhelp\u001b[0m' for available commands."
    }

    streamResponse(term, response)
  }

  private def streamResponse(term: XTermView, text: String): Unit = {
    var index = 0
    def streamNext(): Unit = {
      if (index < text.length) {
        // Handle ANSI escape sequences as atomic units
        if (text(index) == '\u001b') {
          val end = text.indexOf('m', index)
          if (end >= 0) {
            term.write(text.substring(index, end + 1))
            index = end + 1
          } else {
            term.write(text(index).toString)
            index += 1
          }
          streamNext() // ANSI sequences are written instantly
        } else if (text(index) == '\r' && index + 1 < text.length && text(index + 1) == '\n') {
          term.write("\r\n")
          index += 2
          js.timers.setTimeout(15.0)(streamNext())
        } else {
          term.write(text(index).toString)
          index += 1
          js.timers.setTimeout(15.0)(streamNext())
        }
      } else {
        term.write("\r\n")
        writePrompt(term)
      }
    }
    streamNext()
  }
}
