package io.youi.example

import com.outr.scribe.Logging
import io.youi.app.ClientApplication
import io.youi.example.screen.{CommunicationScreen, LoginScreen}

import scala.scalajs.js.JSApp

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with Logging {
  val communication = CommunicationScreen
  val login = LoginScreen

  override def main(): Unit = {
    logger.info("Initialized!")
  }
}