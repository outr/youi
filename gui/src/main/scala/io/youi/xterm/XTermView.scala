package io.youi.xterm

import io.youi.component.Component
import io.youi.dom
import reactify.{Channel, Var}
import xterm.{FitAddon, IResizeEvent, ITerminalOptions, ITheme, Terminal, IDisposable}

import scala.scalajs.js

class XTermView extends Component(dom.create.div) {
  val fontSize: Var[Int] = Var(14)
  val fontFamily: Var[String] = Var("monospace")
  val cursorBlink: Var[Boolean] = Var(true)
  val cursorStyle: Var[String] = Var("block")
  val scrollback: Var[Int] = Var(1000)
  val convertEol: Var[Boolean] = Var(true)
  val disableStdin: Var[Boolean] = Var(false)
  val termBackground: Var[String] = Var("#1e1e1e")
  val termForeground: Var[String] = Var("#d4d4d4")

  val onData: Channel[String] = Channel[String]
  val onResize: Channel[IResizeEvent] = Channel[IResizeEvent]

  private var terminal: Option[Terminal] = None
  private var fitAddon: Option[FitAddon] = None
  private var disposables: List[IDisposable] = Nil

  def write(data: String): Unit = terminal.foreach(_.write(data))
  def writeln(data: String): Unit = terminal.foreach(_.writeln(data))
  def clearTerminal(): Unit = terminal.foreach(_.clear())
  def reset(): Unit = terminal.foreach(_.reset())
  def focus(): Unit = terminal.foreach(_.focus())
  def blur(): Unit = terminal.foreach(_.blur())
  def fit(): Unit = fitAddon.foreach(_.fit())

  def initialize(): Unit = {
    val theme = new ITheme {}
    theme.background = termBackground()
    theme.foreground = termForeground()

    val opts = new ITerminalOptions {}
    opts.fontSize = fontSize()
    opts.fontFamily = fontFamily()
    opts.cursorBlink = cursorBlink()
    opts.cursorStyle = cursorStyle()
    opts.scrollback = scrollback()
    opts.convertEol = convertEol()
    opts.disableStdin = disableStdin()
    opts.theme = theme

    val term = new Terminal(opts)
    terminal = Some(term)

    val fit = new FitAddon
    fitAddon = Some(fit)
    term.loadAddon(fit)

    term.open(element)

    disposables = List(
      term.onData { data =>
        onData @= data
      },
      term.onResize { event =>
        onResize @= event
      }
    )

    // Watch reactive properties and push changes to terminal options
    fontSize.attach { v =>
      terminal.foreach(t => t.asInstanceOf[js.Dynamic].options.fontSize = v)
      this.fit()
    }
    fontFamily.attach { v =>
      terminal.foreach(t => t.asInstanceOf[js.Dynamic].options.fontFamily = v)
      this.fit()
    }
    cursorBlink.attach { v =>
      terminal.foreach(t => t.asInstanceOf[js.Dynamic].options.cursorBlink = v)
    }
    cursorStyle.attach { v =>
      terminal.foreach(t => t.asInstanceOf[js.Dynamic].options.cursorStyle = v)
    }
    scrollback.attach { v =>
      terminal.foreach(t => t.asInstanceOf[js.Dynamic].options.scrollback = v)
    }
    convertEol.attach { v =>
      terminal.foreach(t => t.asInstanceOf[js.Dynamic].options.convertEol = v)
    }
    disableStdin.attach { v =>
      terminal.foreach(t => t.asInstanceOf[js.Dynamic].options.disableStdin = v)
    }
    termBackground.attach { v =>
      terminal.foreach { t =>
        val th = new ITheme {}
        th.background = v
        t.asInstanceOf[js.Dynamic].options.theme = th
      }
    }
    termForeground.attach { v =>
      terminal.foreach { t =>
        val th = new ITheme {}
        th.foreground = v
        t.asInstanceOf[js.Dynamic].options.theme = th
      }
    }

    // Auto-fit when component size changes
    size.width.attach { _ => this.fit() }
    size.height.attach { _ => this.fit() }

    fit.fit()
  }

  def dispose(): Unit = {
    disposables.foreach(_.dispose())
    disposables = Nil
    fitAddon.foreach(_.dispose())
    fitAddon = None
    terminal.foreach(_.dispose())
    terminal = None
  }
}
