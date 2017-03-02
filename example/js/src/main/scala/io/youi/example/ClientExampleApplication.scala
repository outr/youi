package io.youi.example

import scribe.Logging
import io.youi.app.ClientApplication
import io.youi.example.screen.{CommunicationScreen, LoginScreen}
import io.youi.example.ui.UIExamples

import scala.scalajs.js.JSApp
import org.scalajs.dom._
import io.youi.dom._

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with Logging {
  val communicationScreen = CommunicationScreen
  val login = LoginScreen

  val uiExamples = UIExamples

  val loading: html.Div = byId[html.Div]("loading")

  override def main(): Unit = {
    scribe.info("Initialized!")
  }
}