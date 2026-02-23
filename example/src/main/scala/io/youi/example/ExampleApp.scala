package io.youi.example

import rapid.Task
import io.youi._
import io.youi.app.screen.{EmptyScreen, ScreenManager}
import io.youi.component.Container
import io.youi.component.support.MarginSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.example.ui._
import io.youi.example.ui.component.Header
import io.youi.component.types.Display
import io.youi.ui

object ExampleApp extends ScreenManager {
  /** Single shared header; always first child of ui when visible. */
  lazy val appHeader: Header = new Header

  /** Single content area; active screen's container is swapped in as its only child. */
  lazy val contentContainer: Container & MarginSupport = {
    val c = new Container with MarginSupport
    c.id @= "example-content"
    c
  }

  override protected def load(): Task[Unit] = super.load().map { _ =>
    contentContainer.size.width := ui.size.width
    contentContainer.size.height := ui.size.height() - appHeader.size.height()
    ui.children += appHeader
    ui.children += contentContainer
    appHeader.display @= Display.None

    // If no screen matched the URL (e.g. root "/" or an unknown path),
    // fall back to the examples index so the user sees something useful.
    if (active() == EmptyScreen) active @= examples
  }

  override protected def afterScreenChange(oldScreen: io.youi.app.screen.Screen, newScreen: io.youi.app.screen.Screen): Task[Unit] =
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

  val examples: UIExamples = new UIExamples

  def main(args: Array[String]): Unit = {
    scribe.info("Initialized!")
  }
}