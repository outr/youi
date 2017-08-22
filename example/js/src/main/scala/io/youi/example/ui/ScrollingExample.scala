//package io.youi.example.ui
//
//import io.youi._
//import io.youi.app.screen.UIScreen
//import io.youi.component.{Container, DrawableComponent}
//import io.youi.component.layout.VerticalLayout
//import io.youi.style.Paint
//import reactify._
//
//object ScrollingExample extends UIExampleScreen with UIScreen {
//  override def name: String = "Scrolling"
//  override def path: String = "/examples/scrolling.html"
//
//  override def createUI(): Unit = {
//    val scrollable = new Container
//    scrollable.size.width := 250.0
//    scrollable.size.height := 250.0
//
//    val background = Box(Paint.vertical(Color.White, Color.Black), 250.0, 250.0)
//    val black = Box(Color.Black)
//    val red = Box(Color.Red)
//    val green = Box(Color.Green)
//    val blue = Box(Color.Blue)
//
//    red.position.top := black.position.bottom
//    green.position.top := red.position.bottom
//    blue.position.top := green.position.bottom
//
//    scrollable.children ++= List(
//      background,
//      black,
//      red,
//      green,
//      blue
//    )
//
//    container.children += scrollable
//  }
//
//  class Box(w: Double, h: Double) extends DrawableComponent {
//    position.left := 10.0
//    size.width := w
//    size.height := h
//  }
//
//  object Box {
//    def apply(paint: Paint, width: Double = 100.0, height: Double = 100.0): Box = new Box(width, height) {
//      background := paint
//    }
//  }
//}