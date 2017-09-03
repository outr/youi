package io.youi.example

import io.youi.app.screen.{CrossFadeSupport, LoadingTransitionSupport}
import io.youi.app.{ClientApplication, ClientConnectivity}
import io.youi.dom._
import io.youi.example.screen.{CommunicationScreen, LoginScreen}
import io.youi.example.ui._
import io.youi.example.ui.hypertext.DataTransferExample
import org.scalajs.dom._
import scribe.LogHandler

import scala.scalajs.js.JSApp

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with LoadingTransitionSupport {
  val communicationScreen = CommunicationScreen
  val login = LoginScreen

  val uiExamples = UIExamples

  val hello = HelloWorld
  val images = ImageExample
  val video = VideoExample
  val animation = AnimationExample
  val basicText = BasicTextExample
  val text = TextExample
  val htmlComponent = HTMLComponentExample
  val svg = SVGImageExample
  val scale9 = Scale9Example
  val virtual = VirtualSizeExample
  val snap = SnapExample
  val scrolling = ScrollingExample
  val verticalLayout = VerticalLayoutExample
  val gridLayout = GridLayoutExample
  val imageEditor = ImageEditorExample

  val dataTransfer = DataTransferExample
  val selection = hypertext.SelectionExample

  val htmlHello = hypertext.HelloWorld
  val htmlAnimation = hypertext.AnimationExample
  val htmlImages = hypertext.ImageExample
  val htmlLabel = hypertext.LabelExample
  val htmlBorder = hypertext.BorderExample
  val htmlBoxLayout = hypertext.BoxLayoutExample
  val htmlGridLayout = hypertext.GridLayoutExample
  val htmlFlowLayout = hypertext.FlowLayoutExample
  val htmlVirtual = hypertext.VirtualSizeExample
  val htmlPreviewImage = hypertext.PreviewImageExample

  def cc: ClientConnectivity = clientConnectivity(ClientExampleApplication.connectivity)

  override protected val loadingElement: html.Element = byId[html.Div]("loading")

  override def main(): Unit = {
    // Write the JavaScript logging messages to the server console
    scribe.Logger.root.addHandler(LogHandler(writer = ClientApplication.logWriter))

    scribe.info("Initialized!")
  }
}