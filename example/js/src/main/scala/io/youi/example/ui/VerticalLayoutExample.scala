//package io.youi.example.ui
//
//import io.youi._
//import io.youi.app.screen.UIScreen
//import io.youi.component.Container
//import io.youi.layout.VerticalLayout
//import io.youi.paint.{Border, Paint, Stroke}
//
//import scala.concurrent.Future
//
//object VerticalLayoutExample extends UIExampleScreen with UIScreen {
//  override def name: String = "Vertical Layout"
//  override def path: String = "/examples/vertical-layout.html"
//
//  override def createUI(): Future[Unit] = {
//    val boxes = new Container {
//      layout := new VerticalLayout(spacing = 10.0)
//      background := Color.LightBlue
//      border := Border(Stroke(Color.Black), radius = 10.0)
//      position.center := container.position.center
//      position.middle := container.position.middle
//      size.width := 120.0
//      size.height := 475.0
//
//      val black = Box(Color.Black)
//      val red = Box(Color.Red)
//      val green = Box(Color.Green)
//      val blue = Box(Color.Blue)
//      val orange = Box(Color.Orange)
//
//      children ++= List(
//        black,
//        red,
//        green,
//        blue,
//        orange
//      )
//    }
//    container.children += boxes
//    Future.successful(())
//  }
//
//  class Box extends Container {
//    position.left := 10.0
//    size.width := 100.0
//    size.height := 100.0
//  }
//
//  object Box {
//    def apply(paint: Paint): Box = new Box {
//      background := paint
//    }
//  }
//}