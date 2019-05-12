package io.youi.example

import com.outr.hookup.Hookup
import io.youi.app.screen.LoadingTransitionSupport
import io.youi.app.{ClientApplication, ClientConnectivity}
import io.youi.{Color, dom}
import io.youi.dom._
import io.youi.example.screen.{CommunicationScreen, ExampleBootstrapScreen, LoginScreen}
import io.youi.example.ui._
//import io.youi.example.ui._
//import io.youi.example.ui.drawable._
//import io.youi.example.ui.hypertext.DataTransferExample
//import io.youi.util.DebugSupport
import org.scalajs.dom._

import scala.scalajs.js.annotation.JSExportTopLevel

object ClientExampleApplication extends ExampleApplication with ClientApplication with LoadingTransitionSupport {
  object colors {
    object blue {
      val dark: Color = Color.fromLong(0x166196ff)
      val light: Color = Color.fromLong(0x63b4edff)
    }
  }

  // Adds debug support to the application (press F2)
//  val debug = new DebugSupport
//  debug.enabled := true

  val communicationScreen = CommunicationScreen
  val login = LoginScreen
  val bootstrap = ExampleBootstrapScreen

//  val paths = PathsExample
//  val text = TextExample
//  val imageScaling = ImageScalingExample
//  val animatedImage = AnimatedImageExample
//  val heavyText = HeavyTextExample

  val hello: HelloWorld = new HelloWorld

  val animation: AnimationExample = new AnimationExample
  val canvas: CanvasExample = new CanvasExample
  val drawable: DrawableExample = new DrawableExample
  val grid: GridLayoutExample = new GridLayoutExample
  val hit: HitTestExample = new HitTestExample
  val image: ImageExample = new ImageExample
  val imageChange: ImageChangeExample = new ImageChangeExample
  val measured: MeasuredExample = new MeasuredExample
  val parallax: ParallaxExample = new ParallaxExample
  val scale9: Scale9Example = new Scale9Example
  val snap: SnapExample = new SnapExample
  val svgImage: SVGImageExample = new SVGImageExample
  val text: TextViewExample = new TextViewExample
  val input: TextInputExample = new TextInputExample
  val vertical: VerticalLayoutExample = new VerticalLayoutExample
  val video: VideoExample = new VideoExample
  val virtual: VirtualSizeExample = new VirtualSizeExample

  // Bootstrap
  val bootstrapButton: BootstrapButtonExample = new BootstrapButtonExample
  val bootstrapLogin: BootstrapLoginExample = new BootstrapLoginExample

  val examples: UIExamples = new UIExamples

//  val rectangularSelection = RectangularSelectionExample
//  val imageEditor = ImageEditorExample

//  val dataTransfer = DataTransferExample
//  val selection = hypertext.SelectionExample

  val hookup: ExampleHookup = Hookup.client[ExampleHookup]

  override protected val loadingElement: html.Element = getById[html.Div]("loading").getOrElse(dom.create[html.Div]("div"))

  @JSExportTopLevel("application")
  def main(): Unit = {
    // Write the JavaScript logging messages to the server console
    scribe.Logger.root.withHandler(
      writer = ClientApplication.logWriter,
      minimumLevel = Some(scribe.Level.Info)
    ).replace()

    scribe.info("Initialized!")
  }
}