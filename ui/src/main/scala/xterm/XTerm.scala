package xterm

import org.scalajs.dom.KeyboardEvent

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("Terminal")
class Terminal(options: js.UndefOr[ITerminalOptions] = js.undefined) extends js.Object {
  def open(parent: org.scalajs.dom.Element): Unit = js.native
  def write(data: String): Unit = js.native
  def writeln(data: String): Unit = js.native
  def clear(): Unit = js.native
  def reset(): Unit = js.native
  def dispose(): Unit = js.native
  def focus(): Unit = js.native
  def blur(): Unit = js.native
  def resize(cols: Int, rows: Int): Unit = js.native
  def onData(listener: js.Function1[String, Unit]): IDisposable = js.native
  def onKey(listener: js.Function1[IKeyEvent, Unit]): IDisposable = js.native
  def onResize(listener: js.Function1[IResizeEvent, Unit]): IDisposable = js.native
  def onTitleChange(listener: js.Function1[String, Unit]): IDisposable = js.native
  def loadAddon(addon: js.Any): Unit = js.native
  val cols: Int = js.native
  val rows: Int = js.native
}

@js.native
@JSGlobal("FitAddon.FitAddon")
class FitAddon extends js.Object {
  def fit(): Unit = js.native
  def proposeDimensions(): js.UndefOr[IResizeEvent] = js.native
  def dispose(): Unit = js.native
}

@js.native
trait IDisposable extends js.Object {
  def dispose(): Unit = js.native
}

@js.native
trait IKeyEvent extends js.Object {
  val key: String = js.native
  val domEvent: KeyboardEvent = js.native
}

@js.native
trait IResizeEvent extends js.Object {
  val cols: Int = js.native
  val rows: Int = js.native
}

trait ITerminalOptions extends js.Object {
  var fontSize: js.UndefOr[Int] = js.undefined
  var fontFamily: js.UndefOr[String] = js.undefined
  var cursorBlink: js.UndefOr[Boolean] = js.undefined
  var cursorStyle: js.UndefOr[String] = js.undefined
  var theme: js.UndefOr[ITheme] = js.undefined
  var scrollback: js.UndefOr[Int] = js.undefined
  var cols: js.UndefOr[Int] = js.undefined
  var rows: js.UndefOr[Int] = js.undefined
  var convertEol: js.UndefOr[Boolean] = js.undefined
  var disableStdin: js.UndefOr[Boolean] = js.undefined
}

trait ITheme extends js.Object {
  var background: js.UndefOr[String] = js.undefined
  var foreground: js.UndefOr[String] = js.undefined
  var cursor: js.UndefOr[String] = js.undefined
  var cursorAccent: js.UndefOr[String] = js.undefined
  var selectionBackground: js.UndefOr[String] = js.undefined
  var selectionForeground: js.UndefOr[String] = js.undefined
  var black: js.UndefOr[String] = js.undefined
  var red: js.UndefOr[String] = js.undefined
  var green: js.UndefOr[String] = js.undefined
  var yellow: js.UndefOr[String] = js.undefined
  var blue: js.UndefOr[String] = js.undefined
  var magenta: js.UndefOr[String] = js.undefined
  var cyan: js.UndefOr[String] = js.undefined
  var white: js.UndefOr[String] = js.undefined
  var brightBlack: js.UndefOr[String] = js.undefined
  var brightRed: js.UndefOr[String] = js.undefined
  var brightGreen: js.UndefOr[String] = js.undefined
  var brightYellow: js.UndefOr[String] = js.undefined
  var brightBlue: js.UndefOr[String] = js.undefined
  var brightMagenta: js.UndefOr[String] = js.undefined
  var brightCyan: js.UndefOr[String] = js.undefined
  var brightWhite: js.UndefOr[String] = js.undefined
}
