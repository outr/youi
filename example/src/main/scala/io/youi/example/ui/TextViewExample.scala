package io.youi.example.ui

import rapid.Task
import io.youi.Color
import io.youi.component.support.PaddingSupport
import io.youi.component.types.PositionType
import io.youi.component.{Container, TextView}
import io.youi.example.ExampleApp
import io.youi.example.screen.UIExampleScreen
import io.youi._
import io.youi.font.GoogleFont
import spice.net._
import reactify._

class TextViewExample extends UIExampleScreen {
  override def title: String = "TextView Example"
  override def path: URLPath = path"/examples/text.html"

  override def createUI(): Task[Unit] = {
    for {
      openSans <- GoogleFont.`Open Sans`.`regular`.load()
      pacifico <- GoogleFont.`Pacifico`.`regular`.load()
      roboto <- GoogleFont.`Roboto`.`900`.load()
      berkshire <- GoogleFont.`Berkshire Swash`.`regular`.load()
    } yield {
      val openSansView = new TextView {
        content @= "Open Sans"
        font.weight @= openSans
        font.size @= 96.0
        color := ExampleApp.textColor
        position.`type` @= PositionType.Absolute
        position.x @= 100.0
        position.y @= 100.0
      }
      container.children += new Container {
        position.`type` @= PositionType.Absolute
        position.x @= 100.0
        position.y @= 100.0
        size.width := openSansView.measured.width
        size.height := openSansView.measured.height
        backgroundColor := ExampleApp.subtleBg
      }
      val pacificoView = new TextView with PaddingSupport {
        content @= "Pacifico Regular"
        font.weight @= pacifico
        font.size @= 128.0
        color @= Color.Red
//        fill := Paint.horizontal(size.width).distributeColors(Color.Red, Color.Green, Color.Blue)
        position.`type` @= PositionType.Absolute
        position.center := container.size.center
        position.middle := container.size.middle
//        border := Border(Stroke(Color.Purple, None, 2.0), 10.0)
        padding @= 10.0
      }
      val robotoView = new TextView {
        content @= "Roboto 900"
        font.weight @= roboto
        font.size @= 96.0
//        Paint.image("/images/cuteness.jpg").foreach(fill @= _)
//        stroke @= Stroke(Color.Black, None, lineWidth = 0.5)
        position.`type` @= PositionType.Absolute
        position.center := container.size.center
        position.bottom := pacificoView.position.top - 20.0
      }
      val berkshireView = new TextView {
        content @= "Berkshire Swash Regular"
//        cache := false
//        font.file := berkshire
        font.weight @= berkshire
        font.size @= 96.0
//        Paint.video(History.url().withPath("/sample.mp4")).foreach(fill := _)
//        stroke := Stroke(Color.Black, None, lineWidth = 0.5)
        position.`type` @= PositionType.Absolute
        position.center := container.size.center
        position.top := pacificoView.position.bottom + 20.0
      }

      container.children ++= Seq(openSansView, pacificoView, robotoView, berkshireView)
    }
  }
}
