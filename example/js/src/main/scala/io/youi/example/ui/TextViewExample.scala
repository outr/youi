package io.youi.example.ui

import io.youi.component.{Container, TextView}
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import io.youi.net._
import io.youi.Color
import io.youi.component.support.{MeasuredSupport, PaddingSupport, PositionSupport, SizeSupport}
import io.youi.component.types.PositionType
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TextViewExample extends UIExampleScreen {
  override def title: String = "TextView Example"
  override def path: Path = path"/examples/text.html"

  override def createUI(): Future[Unit] = {
    for {
      openSans <- GoogleFont.`Open Sans`.`regular`.load()
      pacifico <- GoogleFont.`Pacifico`.`regular`.load()
      roboto <- GoogleFont.`Roboto`.`900`.load()
      berkshire <- GoogleFont.`Berkshire Swash`.`regular`.load()
    } yield {
      val openSansView = new TextView with PositionSupport with MeasuredSupport {
        content @= "Open Sans"
        font.weight @= openSans
        font.size @= 96.0
        color @= Color.Black
        position.`type` @= PositionType.Absolute
        position.x @= 100.0
        position.y @= 100.0
      }
      container.children += new Container with PositionSupport with SizeSupport {
        position.`type` @= PositionType.Absolute
        position.x @= 100.0
        position.y @= 100.0
        size.width := openSansView.measured.width
        size.height := openSansView.measured.height
        background @= Color.Yellow
      }
      val pacificoView = new TextView with PositionSupport with PaddingSupport with MeasuredSupport {
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
      val robotoView = new TextView with PositionSupport with MeasuredSupport {
        content @= "Roboto 900"
        font.weight @= roboto
        font.size @= 96.0
//        Paint.image("/images/cuteness.jpg").foreach(fill @= _)
//        stroke @= Stroke(Color.Black, None, lineWidth = 0.5)
        position.`type` @= PositionType.Absolute
        position.center := container.size.center
        position.bottom := pacificoView.position.top - 20.0
      }
      val berkshireView = new TextView with PositionSupport with MeasuredSupport {
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
