//package io.youi.example.ui
//
//import io.youi._
//import io.youi.app.screen.UIScreen
//import io.youi.component.{Container, HTMLComponent}
//import io.youi.hypertext.Button
//import io.youi.paint.{Border, Stroke}
//
//object HTMLComponentExample extends UIExampleScreen with UIScreen {
//  override def name: String = "HTMLComponent Example"
//  override def path: String = "/examples/html-component.html"
//
//  override def createUI(): Unit = {
//    container.children += new Container {
//      position.center := container.position.center
//      position.middle := container.position.middle
//      size.width := 500.0
//      size.height := 500.0
//      background := Color.SkyBlue
//      border := Border(Stroke(Color.DarkBlue, 2.0), 10.0)
//
//      val button = new Button {
//        text := "Hello, World!"
//        font.family := "Arial"
//        font.size := 48.0
//      }
//      val component = new HTMLComponent(button) {
//        position.left := 100.0
//        position.top := 100.0
//      }
//      children += component
//    }
//  }
//}
