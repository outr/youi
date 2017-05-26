package io.youi.example

import io.youi.UI
import io.youi.app.{ClientApplication, ClientConnectivity}
import io.youi.app.screen.CrossFadeSupport
import io.youi.component.Renderer
import io.youi.example.screen.{CommunicationScreen, LoginScreen}

import scala.scalajs.js.JSApp
import org.scalajs.dom._
import io.youi.dom._
import io.youi.example.ui._

import scala.concurrent.duration._

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with CrossFadeSupport {
  val communicationScreen = CommunicationScreen
  val login = LoginScreen

  val uiExamples = UIExamples

  val hello = HelloWorld
  val images = ImageExample
  val animation = AnimationExample
  val graphics = GraphicsExample
  val text = TextExample
  val canvas = CanvasExample
  val htmlComponent = HTMLComponentExample
  val virtual = VirtualSizeExample
  val imageEditor = ImageEditorExample

  val htmlHello = hypertext.HelloWorld
  val htmlAnimation = hypertext.AnimationExample
  val htmlImages = hypertext.ImageExample
  val htmlLabel = hypertext.LabelExample
  val htmlBorder = hypertext.BorderExample
  val htmlBoxLayout = hypertext.BoxLayoutExample
  val htmlGridLayout = hypertext.GridLayoutExample
  val htmlFlowLayout = hypertext.FlowLayoutExample
  val htmlVirtual = hypertext.VirtualSizeExample

  def cc: ClientConnectivity = clientConnectivity(ClientExampleApplication.connectivity)

  override protected val crossFadeElement: html.Div = byId[html.Div]("loading")
  override protected val crossFadeDuration: FiniteDuration = 250.milliseconds

  override def main(): Unit = {
    UI.init()
    scribe.info("Initialized!")
  }
}