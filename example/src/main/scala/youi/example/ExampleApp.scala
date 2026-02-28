package youi.example

import rapid.Task
import youi._
import youi.app.screen.{EmptyScreen, ScreenManager}
import youi.component.Container
import youi.component.support.MarginSupport
import youi.example.screen.UIExampleScreen
import youi.example.ui._
import youi.example.ui.component.Header
import youi.component.types.Display
import youi.storage.LocalStorage
import youi.ui
import fabric.rw._
import reactify._

object ExampleApp extends ScreenManager {
  val darkMode: Var[Boolean] = Var(false)

  // Reactive theme colors derived from darkMode
  val textColor: Val[Color] = Val(if (darkMode()) Color.fromHex("e0e0e0") else Color.fromHex("212121"))
  val secondaryText: Val[Color] = Val(if (darkMode()) Color.fromHex("b0b0b0") else Color.fromHex("666666"))
  val accentColor: Val[Color] = Val(if (darkMode()) Color.fromHex("90caf9") else Color.fromHex("3949ab"))
  val bgColor: Val[Color] = Val(if (darkMode()) Color.fromHex("121212") else Color.White)
  val surfaceColor: Val[Color] = Val(if (darkMode()) Color.fromHex("1e1e1e") else Color.White)
  val subtleBg: Val[Color] = Val(if (darkMode()) Color.fromHex("1a1a2e") else Color.fromHex("f0f4ff"))
  val borderColor: Val[Color] = Val(if (darkMode()) Color.fromHex("444444") else Color.fromHex("e0e0e0"))
  val buttonBg: Val[Color] = Val(if (darkMode()) Color.fromHex("5c6bc0") else Color.SteelBlue)
  val buttonText: Val[Color] = Val(Color.White)

  /** Single shared header; always first child of ui when visible. */
  lazy val appHeader: Header = new Header

  /** Single content area; active screen's container is swapped in as its only child. */
  lazy val contentContainer: Container & MarginSupport = {
    val c = new Container with MarginSupport
    c.id @= "example-content"
    c
  }

  override protected def load(): Task[Unit] = super.load().flatMap { _ =>
    // Restore dark mode preference from localStorage
    LocalStorage.connect[Boolean]("youi-dark-mode", darkMode).map { _ =>
      contentContainer.size.width := ui.size.width
      contentContainer.size.height := ui.size.height() - appHeader.size.height()
      contentContainer.backgroundColor := bgColor
      ui.children += appHeader
      ui.children += contentContainer
      appHeader.display @= Display.None

      // If no screen matched the URL (e.g. root "/" or an unknown path),
      // fall back to the examples index so the user sees something useful.
      if (active() == EmptyScreen) active @= examples
    }
  }

  override protected def afterScreenChange(oldScreen: youi.app.screen.Screen, newScreen: youi.app.screen.Screen): Task[Unit] =
    super.afterScreenChange(oldScreen, newScreen).map { _ =>
      val showHeader = newScreen.isInstanceOf[UIExampleScreen]
      appHeader.display @= (if (showHeader) Display.Block else Display.None)
    }

  val hello: HelloWorld = new HelloWorld

  val chat: ChatExample = new ChatExample
  val animatedImage: AnimatedImageExample = new AnimatedImageExample
  val animation: AnimationExample = new AnimationExample
  val canvas: CanvasExample = new CanvasExample
  val dataTransfer: DataTransferExample = new DataTransferExample
  val drawable: DrawableExample = new DrawableExample
  val drop: DropExample = new DropExample
  val flow: FlowLayoutExample = new FlowLayoutExample
  val gestures: GesturesExample = new GesturesExample
  val grid: GridLayoutExample = new GridLayoutExample
  val horizontal: HorizontalLayoutExample = new HorizontalLayoutExample
  val heavyText: HeavyTextExample = new HeavyTextExample
  val materialIcons: MaterialIconsExample = new MaterialIconsExample
  val phosphor: PhosphorExample = new PhosphorExample
  val image: ImageExample = new ImageExample
  val imageChange: ImageChangeExample = new ImageChangeExample
  val mdc: MDCExample = new MDCExample
  val measured: MeasuredExample = new MeasuredExample
  val modal: ModalExample = new ModalExample
  val parallax: ParallaxExample = new ParallaxExample
  val popup: PopupExample = new PopupExample
  val scale9: Scale9Example = new Scale9Example
  val snap: SnapExample = new SnapExample
  val scrolling: ScrollingExample = new ScrollingExample
  val select: SelectExample = new SelectExample
  val selection: SelectionExample = new SelectionExample
  val sidebar: SidebarExample = new SidebarExample
  val svgImage: SVGImageExample = new SVGImageExample
  val text: TextViewExample = new TextViewExample
  val textInput: TextInputExample = new TextInputExample
  val vertical: VerticalLayoutExample = new VerticalLayoutExample
  val video: VideoExample = new VideoExample
  val monacoEditor: MonacoExample = new MonacoExample
  val xterm: XTermExample = new XTermExample
  val tabulator: TabulatorExample = new TabulatorExample

  val examples: UIExamples = new UIExamples

  def main(args: Array[String]): Unit = {
    scribe.info("Initialized!")
  }
}