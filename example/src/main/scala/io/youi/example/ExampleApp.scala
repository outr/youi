package io.youi.example

import io.youi.app.screen.ScreenManager
import io.youi.example.ui.HelloWorld
import typekit.WebFont

object ExampleApp extends ScreenManager {
  private val helloWorld = new HelloWorld

  def main(args: Array[String]): Unit = {
    scribe.info("Hello, World!")
  }
}