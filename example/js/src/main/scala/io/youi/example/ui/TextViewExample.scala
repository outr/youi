//package io.youi.example.ui
//
//import io.youi.app.screen.UIScreen
//import io.youi.component.{Container, TextView}
//import io.youi.font.{GoogleFont, OpenTypeFont}
//import io.youi.paint.{Border, Paint, Stroke}
//import io.youi.{Color, History, ui}
//import reactify._
//
//import scala.concurrent.Future
//
//object TextViewExample extends UIExampleScreen with UIScreen {
//  override def name: String = "TextView Example"
//  override def path: String = "/examples/text.html"
//
//  override def createUI(): Future[Unit] = {
//    for {
//      openSans <- OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`)
//      pacifico <- OpenTypeFont.fromURL(GoogleFont.`Pacifico`.`regular`)
//      roboto <- OpenTypeFont.fromURL(GoogleFont.`Roboto`.`900`)
//      berkshire <- OpenTypeFont.fromURL(GoogleFont.`Berkshire Swash`.`regular`)
//    } yield {
//      val openSansView = new TextView {
//        value := "Open Sans"
//        font.file := openSans
//        font.size := 96.0
//        fill := Color.Black
//        position.x := 100.0
//        position.y := 100.0
//      }
//      container.children += new Container {
//        position.x := 100.0
//        position.y := 100.0
//        size.width := openSansView.size.width
//        size.height := openSansView.size.height
//        background := Color.Yellow
//      }
//      val pacificoView = new TextView {
//        value := "Pacifico Regular"
//        font.file := pacifico
//        font.size := 128.0
//        fill := Paint.horizontal(size.width).distributeColors(Color.Red, Color.Green, Color.Blue)
//        position.center := ui.center
//        position.middle := ui.middle
//        border := Border(Stroke(Color.Purple, None, 2.0), 10.0)
//        padding := 10.0
//      }
//      val robotoView = new TextView {
//        value := "Roboto 900"
//        font.file := roboto
//        font.size := 96.0
//        // TODO: fix calling `:=` perpetually calling load
//        Paint.image("/images/cuteness.jpg").foreach(fill := _)
//        stroke := Stroke(Color.Black, None, lineWidth = 0.5)
//        position.center := ui.center
//        position.bottom := pacificoView.position.top - 20.0
//      }
//      val berkshireView = new TextView {
//        value := "Berkshire Swash Regular"
//        cache := false
//        font.file := berkshire
//        font.size := 96.0
//        Paint.video(History.url().withPath("/sample.mp4")).foreach(fill := _)
//        stroke := Stroke(Color.Black, None, lineWidth = 0.5)
//        position.center := ui.center
//        position.top := pacificoView.position.bottom + 20.0
//      }
//
//      container.children ++= Seq(openSansView, pacificoView, robotoView, berkshireView)
//    }
//  }
//}
