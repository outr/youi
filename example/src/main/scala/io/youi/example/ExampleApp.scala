package io.youi.example

import io.youi.app.screen.ScreenManager
import io.youi.example.ui._
import typekit.WebFont

object ExampleApp extends ScreenManager {
  val hello: HelloWorld = new HelloWorld

  val animation: AnimationExample = new AnimationExample
  val canvas: CanvasExample = new CanvasExample
  val drawable: DrawableExample = new DrawableExample
  val drop: DropExample = new DropExample
  val fontAwesome: FontAwesomeExample = new FontAwesomeExample
  val image: ImageExample = new ImageExample
  val imageChange: ImageChangeExample = new ImageChangeExample
  val mdc: MDCExample = new MDCExample
  val popup: PopupExample = new PopupExample
  val scrolling: ScrollingExample = new ScrollingExample
  val sidebar: SidebarExample = new SidebarExample
  val text: TextViewExample = new TextViewExample

  val examples: UIExamples = new UIExamples

  def main(args: Array[String]): Unit = {
    scribe.info("Initialized!")
  }
}