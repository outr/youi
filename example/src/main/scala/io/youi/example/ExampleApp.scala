package io.youi.example

import io.youi.app.screen.ScreenManager
import io.youi.example.ui.{AnimationExample, HelloWorld}
import typekit.WebFont

object ExampleApp extends ScreenManager {
  private val helloWorld = new HelloWorld

  private val animation = new AnimationExample

  def main(args: Array[String]): Unit = {
//    active @= animation
  }
}