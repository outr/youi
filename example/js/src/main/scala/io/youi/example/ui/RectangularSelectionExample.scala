//package io.youi.example.ui
//
//import io.youi._
//import io.youi.app.screen.UIScreen
//import io.youi.component.TextView
//import io.youi.component.extra.RectangularSelection
//import io.youi.font.{GoogleFont, OpenTypeFont}
//import io.youi.net.URL
//
//import scala.concurrent.Future
//
//object RectangularSelectionExample extends UIExampleScreen with UIScreen {
//  override def name: String = "Rectangular Selection"
//  override def path: String = "/examples/rectangular-selection.html"
//
//  val fontURL: URL = GoogleFont.`Open Sans`.`regular`
//
//  override def createUI(): Future[Unit] = OpenTypeFont.fromURL(fontURL).map { fnt =>
//    container.children += new TextView {
//      font.file := fnt
//      font.size := 48.0
//      value := "Hello, World!"
//      fill := Color.DarkBlue
//      position.center := ui.center
//      position.middle := ui.middle
//    }
//    container.children += new RectangularSelection {
//      size.width := ui.width
//      size.height := ui.height
//      selection.x1 := 100.0
//      selection.y1 := 100.0
//      selection.x2 := 300.0
//      selection.y2 := 300.0
//    }
//  }
//}