//package youi.example.ui.hypertext
//
//import youi.hypertext.border.BorderStyle
//import youi.hypertext.{Container, Label}
//import youi.{Color, ui}
//
//import scala.concurrent.Future
//
//object BorderExample extends HTMLScreen {
//  override def name: String = "HTML Border Example"
//
//  override protected def load(): Future[Unit] = super.load().map { _ =>
//    container.children += new Container {
//      position.center := ui.center
//      position.middle := ui.middle
//      size.width := 400.0
//      size.height := 300.0
//      backgroundColor := Color.DarkCyan
//      border.radius := 10.0
//      border.color := Some(Color.DarkBlue)
//      border.size := Some(5.0)
//      border.style := Some(BorderStyle.Dashed)
//      border.bottom.right.radius := 0.0
//    }
//
//    container.children += new Label {
//      color := Color.White
//      font.size := 42.0
//      font.family := "sans-serif"
//      text := "Border Example"
//
//      position.center := ui.center
//      position.middle := ui.middle
//    }
//  }
//
//  override def path: String = "/examples/html/border.html"
//}
