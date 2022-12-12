//package io.youi.example.ui
//
//import io.youi.Color
//import io.youi.component.HTMLTextInput
//import io.youi.example.screen.UIExampleScreen
//import io.youi.font.GoogleFont
//import spice.net._
//import reactify._
//
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.concurrent.Future
//
//class TextInputExample extends UIExampleScreen {
//  override def title: String = "TextInput Example"
//  override def path: Path = path"/examples/input.html"
//
//  override def createUI(): Future[Unit] = for {
//    pacifico <- GoogleFont.`Pacifico`.`regular`.load()
//    roboto <- GoogleFont.`Roboto`.`900`.load()
//    berkshire <- GoogleFont.`Berkshire Swash`.`regular`.load()
//  } yield {
//    val pacificoView = new HTMLTextInput {
//      value @= "Pacifico Regular"
//      font @= pacifico
//      font.size @= 64.0
//      color @= Color.Red
//      position.center := container.size.center
//      position.middle := container.size.middle
//      padding @= 10.0
//    }
//    val robotoView = new HTMLTextInput {
//      value @= "Roboto 900"
//      font @= roboto
//      color @= Color.Green
//      font.size @= 64.0
//      position.center := container.size.center
//      position.bottom := pacificoView.position.top - 30.0
//    }
//    val berkshireView = new HTMLTextInput {
//      value @= "Berkshire Swash Regular"
//      font @= berkshire
//      color @= Color.Blue
//      font.size @= 64.0
//      size.width @= 900.0
//      value.attach { v =>
//        scribe.info(s"Value: $v")
//      }
//      position.center := container.size.center
//      position.top := pacificoView.position.bottom + 30.0
//    }
//
//    container.children ++= Seq(pacificoView, robotoView, berkshireView)
//  }
//}
