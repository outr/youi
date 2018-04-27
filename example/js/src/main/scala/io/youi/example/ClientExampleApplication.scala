package io.youi.example

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

//  val uiExamples = UIExamples

//  val paths = PathsExample
//  val text = TextExample
//  val imageScaling = ImageScalingExample
//  val animatedImage = AnimatedImageExample
//  val heavyText = HeavyTextExample

  val hello: HelloWorld = new HelloWorld
  val animation: AnimationExample = new AnimationExample
  val grid: GridLayoutExample = new GridLayoutExample
  val hit: HitTestExample = new HitTestExample
  val image: ImageExample = new ImageExample
  val imageChange: ImageChangeExample = new ImageChangeExample
  val scale9: Scale9Example = new Scale9Example
  val snap: SnapExample = new SnapExample

//  val imageChange = ImageChangeExample
//  val hitTest = HitTestExample
//  val images = ImageExample
//  val video = VideoExample
//  val animation = AnimationExample
//  val textView = TextViewExample
//  val htmlComponent = HTMLComponentExample
//  val svg = SVGImageExample
//  val scale9 = Scale9Example
//  val virtual = VirtualSizeExample
//  val snap = SnapExample
//  val scrolling = ScrollingExample
//  val verticalLayout = VerticalLayoutExample
//  val gridLayout = GridLayoutExample
//  val rectangularSelection = RectangularSelectionExample
//  val imageEditor = ImageEditorExample

//  val dataTransfer = DataTransferExample
//  val selection = hypertext.SelectionExample

//  val htmlHello = hypertext.HelloWorld
//  val htmlAnimation = hypertext.AnimationExample
//  val htmlImages = hypertext.ImageExample
//  val htmlLabel = hypertext.LabelExample
//  val htmlBorder = hypertext.BorderExample
//  val htmlBoxLayout = hypertext.VerticalLayoutExample
//  val htmlGridLayout = hypertext.GridLayoutExample
//  val htmlFlowLayout = hypertext.FlowLayoutExample
//  val htmlVirtual = hypertext.VirtualSizeExample
//  val htmlPreviewImage = hypertext.PreviewImageExample

  def cc: ClientConnectivity = clientConnectivity(ClientExampleApplication.connectivity)

  override protected val loadingElement: html.Element = getById[html.Div]("loading").getOrElse(dom.create[html.Div]("div"))

  @JSExportTopLevel("application")
  def main(): Unit = {
    // Write the JavaScript logging messages to the server console
    scribe.Logger.update(scribe.Logger.rootName)(_.withHandler(writer = ClientApplication.logWriter))

    scribe.info("Initialized!")
  }
}