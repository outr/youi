package monaco

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("monaco")
object Monaco extends js.Object {
  val editor: MonacoEditorModule = js.native
  val languages: MonacoLanguagesModule = js.native
}

@js.native
trait MonacoEditorModule extends js.Object {
  def create(element: org.scalajs.dom.Element, options: js.UndefOr[EditorOptions] = js.undefined): IStandaloneCodeEditor = js.native
  def setTheme(themeName: String): Unit = js.native
}

@js.native
trait IStandaloneCodeEditor extends js.Object {
  def getValue(): String = js.native
  def setValue(value: String): Unit = js.native
  def dispose(): Unit = js.native
  def layout(dimension: js.UndefOr[EditorDimension] = js.undefined): Unit = js.native
  def onDidChangeModelContent(listener: js.Function1[js.Any, Unit]): IDisposable = js.native
  def updateOptions(options: EditorOptions): Unit = js.native
  def focus(): Unit = js.native
  def getModel(): ITextModel = js.native
}

@js.native
trait ITextModel extends js.Object {
  def getValue(): String = js.native
  def setValue(value: String): Unit = js.native
  def getLineCount(): Int = js.native
  def dispose(): Unit = js.native
}

@js.native
trait IDisposable extends js.Object {
  def dispose(): Unit = js.native
}

@js.native
trait IPosition extends js.Object {
  val lineNumber: Int = js.native
  val column: Int = js.native
}

@js.native
trait MonacoLanguagesModule extends js.Object {
  def getLanguages(): js.Array[js.Dynamic] = js.native
}

trait EditorOptions extends js.Object {
  var value: js.UndefOr[String] = js.undefined
  var language: js.UndefOr[String] = js.undefined
  var theme: js.UndefOr[String] = js.undefined
  var readOnly: js.UndefOr[Boolean] = js.undefined
  var minimap: js.UndefOr[MinimapOptions] = js.undefined
  var fontSize: js.UndefOr[Int] = js.undefined
  var wordWrap: js.UndefOr[String] = js.undefined
  var automaticLayout: js.UndefOr[Boolean] = js.undefined
  var scrollBeyondLastLine: js.UndefOr[Boolean] = js.undefined
  var tabSize: js.UndefOr[Int] = js.undefined
  var lineNumbers: js.UndefOr[String] = js.undefined
}

trait MinimapOptions extends js.Object {
  var enabled: js.UndefOr[Boolean] = js.undefined
}

trait EditorDimension extends js.Object {
  var width: Double
  var height: Double
}
