package io.youi.example

import io.youi.UI
import io.youi.app.{ClientApplication, ClientConnectivity}
import io.youi.app.screen.CrossFadeSupport
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

  val hello = hypertext.HelloWorld
  val animation = hypertext.AnimationExample
  val images = hypertext.ImageExample
  val label = hypertext.LabelExample
  val border = hypertext.BorderExample
  val boxLayout = hypertext.BoxLayoutExample
  val gridLayout = hypertext.GridLayoutExample
  val flowLayout = hypertext.FlowLayoutExample
  val virtual = hypertext.VirtualSizeExample

  val uiImages = ImageExample

  def cc: ClientConnectivity = clientConnectivity(ClientExampleApplication.connectivity)

  override protected val crossFadeElement: html.Div = byId[html.Div]("loading")
  override protected val crossFadeDuration: FiniteDuration = 250.milliseconds

  override def main(): Unit = {
    UI.init()
    scribe.info("Initialized!")
  }
}