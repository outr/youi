package youi.example.ui

import rapid.Task
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.xterm.{TerminalCommand, TerminalShell, XTermLoader, XTermView}
import reactify.stateful2Value
import scribe.ANSI
import spice.net._

import scala.scalajs.js

class XTermExample extends UIExampleScreen {
  override def title: String = "XTerm"
  override def path: URLPath = path"/examples/xterm.html"

  override def createUI(): Task[Unit] = XTermLoader.load().map { _ =>
    val term = new XTermView with TerminalShell
    term.size.width := container.size.width
    term.size.height := container.size.height

    // Set initial theme based on current dark mode state
    if (!ExampleApp.darkMode()) {
      term.termBackground @= "#ffffff"
      term.termForeground @= "#333333"
    }

    term.prompt @= s"${ANSI.fg.BrightGreen("youi")}${ANSI.fg.BrightYellow(">")} "
    term.banner @= s"${ANSI.fg.BrightCyan("╔══════════════════════════════════════╗")}\r\n" +
      s"${ANSI.fg.BrightCyan("║")}  ${ANSI.fg.BrightYellow("YouI Terminal")}                       ${ANSI.fg.BrightCyan("║")}\r\n" +
      s"${ANSI.fg.BrightCyan("║")}  ${ANSI.fg.White("Type 'help' for available commands")}  ${ANSI.fg.BrightCyan("║")}\r\n" +
      s"${ANSI.fg.BrightCyan("╚══════════════════════════════════════╝")}"

    term.register(
      TerminalCommand("echo", "Echo text back", "<text>") { (args, _) =>
        if (args.nonEmpty) args else "Usage: echo <text>"
      },
      TerminalCommand("date", "Show the current date and time") { (_, _) =>
        new js.Date().toString()
      },
      TerminalCommand("clear", "Clear the terminal") { (_, shell) =>
        shell.clearTerminal()
        shell.writePrompt()
        ""
      },
      TerminalCommand("hello", "A friendly greeting") { (_, _) =>
        s"${ANSI.fg.BrightMagenta("Hello from YouI!")} ${ANSI.fg.White("Welcome to the terminal.")}"
      },
      TerminalCommand("about", "About YouI Terminal") { (_, _) =>
        s"${ANSI.fg.BrightCyan("YouI Terminal")}\r\nA terminal emulator powered by xterm.js\r\nBuilt with the YouI UI framework for Scala.js"
      },
      TerminalCommand("colors", "Show ANSI color palette") { (_, _) =>
        val normal = (0 until 8).map(i => s"\u001b[4${i}m  \u001b[0m").mkString
        val bright = (0 until 8).map(i => s"\u001b[10${i}m  \u001b[0m").mkString
        s"Normal:  $normal\r\nBright:  $bright"
      }
    )

    container.children += term
    term.initialize()

    ExampleApp.darkMode.attach { isDark =>
      if (isDark) {
        term.termBackground @= "#1e1e1e"
        term.termForeground @= "#d4d4d4"
      } else {
        term.termBackground @= "#ffffff"
        term.termForeground @= "#333333"
      }
    }
  }
}
