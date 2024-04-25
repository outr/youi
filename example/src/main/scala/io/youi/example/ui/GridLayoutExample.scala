//package io.youi.example.ui
//
//import io.youi._
//import io.youi.component.Container
//import io.youi.example.screen.UIExampleScreen
//import io.youi.layout.GridLayout
//import spice.net._
//import io.youi.paint.Paint
//import reactify._
//
//import scala.concurrent.Future
//
//class GridLayoutExample extends UIExampleScreen {
//  override def title: String = "Grid Layout"
//  override def path: Path = path"/examples/grid.html"
//
//  override def createUI(): Future[Unit] = {
//    val grid = new Container {
//      background @= Color.AliceBlue
//      padding @= 15.0
////      border := Border(Stroke(Color.Black))
//      position.center := container.size.center
//      position.middle := container.size.middle
//    }
//    grid.layout @= new GridLayout {
//      columns @= 3
//      config.default.margin.left @= Some(10.0)
//      config.cell(1, 1).margin.right @= Some(10.0)
//      config.cell(2, 0).span.columns @= Some(2)
//      config.cell(2, 0).alignment.horizontal @= Some(Horizontal.Center)
//    }
//
//    grid.children ++= List(
//      Box(Color.Black),
//      Box(Color.Red),
//      Box(Color.Green),
//      Box(Color.Blue),
//      Box(Color.Purple),
//      Box(Color.Pink),
//      Box(Color.Orange),
//      Box(Color.Yellow),
//      Box(Color.LightBlue),
//      Box(Color.Fuchsia)
//    )
//
//    container.children += grid
//
//    Future.successful(())
//  }
//
//  class Box extends Container {
//    position.left @= 10.0
//    size.width @= 100.0
//    size.height @= 100.0
//  }
//
//  object Box {
//    def apply(paint: Paint): Box = new Box {
//      background @= paint
//    }
//  }
//}