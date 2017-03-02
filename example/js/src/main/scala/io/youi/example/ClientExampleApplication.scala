package io.youi.example

import io.youi.app.ClientApplication
import io.youi.app.screen.CrossFadeSupport
import io.youi.example.screen.{CommunicationScreen, LoginScreen}
import io.youi.example.ui.UIExamples

import scala.scalajs.js.JSApp
import org.scalajs.dom._
import io.youi.dom._

import scala.concurrent.duration._

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with CrossFadeSupport {
  val communicationScreen = CommunicationScreen
  val login = LoginScreen

  val uiExamples = UIExamples

  override protected val crossFadeElement: html.Div = byId[html.Div]("loading")
  override protected val crossFadeDuration: FiniteDuration = 250.milliseconds

  override def main(): Unit = {
    scribe.info("Initialized!")
  }
}