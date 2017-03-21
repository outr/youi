package io.youi.example

import io.youi.UI
import io.youi.app.{ClientApplication, ClientConnectivity}
import io.youi.app.screen.CrossFadeSupport
import io.youi.example.screen.{CommunicationScreen, LoginScreen}
import io.youi.example.ui._

import scala.scalajs.js.JSApp
import org.scalajs.dom._
import io.youi.dom._

import scala.concurrent.duration._

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with CrossFadeSupport {
  val communicationScreen = CommunicationScreen
  val login = LoginScreen

  val uiExamples = UIExamples

  val hello = HelloWorld
  val animation = AnimationExample
  val images = ImageExample
  val label = LabelExample
  val border = BorderExample
  val boxLayout = BoxLayoutExample
  val gridLayout = GridLayoutExample
  val flowLayout = FlowLayoutExample
  val virtual = VirtualSizeExample

  def cc: ClientConnectivity = clientConnectivity(ClientExampleApplication.connectivity)

  override protected val crossFadeElement: html.Div = byId[html.Div]("loading")
  override protected val crossFadeDuration: FiniteDuration = 250.milliseconds

  override def main(): Unit = {
    UI.init()
    scribe.info("Initialized!")
  }
}