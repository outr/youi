//package io.youi.example.ui
//
//import io.youi._
//import io.youi.app.screen.UIScreen
//import io.youi.component.{BasicText, Container, ScrollSupport}
//import io.youi.paint.{Border, Stroke}
//
//object HelloWorld extends UIExampleScreen with UIScreen {
//  override def name: String = "Hello World"
//  override def path: String = "/examples/hello.html"
//
//  override def createUI(): Unit = {
//    container.children += new Container with ScrollSupport {
//      background := Color.Red
//      size.width := 800.0
//      size.height := 600.0
//      position.left := 100.0
//      position.top := 50.0
//
//      children += new Container {
//        background := Color.Green
//        size.width := 600.0
//        size.height := 400.0
//        position.left := 100.0
//        position.top := 500.0
//
//        children += new BasicText {
//          value := "Hello, World!"
//          font.size := 48.0
//          fill := Color.DarkBlue
//          background := Color.LightBlue
//          padding.left := 60.0
//          border := Border(Stroke(Color.Red, lineWidth = 2.0), 10.0)
////          position.center := ui.position.center
////          position.middle := ui.position.middle
//          position.left := 100.0
//          position.top := 50.0
//        }
//      }
//    }
//  }
//}