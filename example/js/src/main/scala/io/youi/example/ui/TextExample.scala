package io.youi.example.ui

import io.youi.ui
import io.youi.Color
import io.youi.app.screen.UIScreen
import io.youi.component.{Text, Video}
import io.youi.font.{Font, GoogleFont}
import io.youi.paint.{Border, Paint, Stroke}
import reactify._

object TextExample extends UIExampleScreen with UIScreen {
  override def name: String = "Text Example"
  override def path: String = "/examples/text.html"

  override def createUI(): Unit = {
    val pacifico = new Text {
      value := "Pacifico Regular"
      font.file := Font.fromURL(GoogleFont.`Pacifico`.`regular`)
      font.size := 128.0
      fill := Paint.horizontal(this).distributeColors(Color.Red, Color.Green, Color.Blue)
      position.center := container.position.center
      position.middle := container.position.middle
      border := Border(Stroke(Color.Purple, 2.0), 10.0)
      padding := 10.0
    }
    val roboto = new Text {
      value := "Roboto 900"
      font.file := Font.fromURL(GoogleFont.`Roboto`.`900`)
      font.size := 96.0
      // TODO: fix calling `:=` perpetually calling load
      fill.static(Paint.image("/images/cuteness.jpg"))
      stroke := Stroke(Color.Black, lineWidth = 0.5)
      position.center := container.position.center
      position.bottom := pacifico.position.top - 20.0
    }
    val berkshire = new Text {
      value := "Berkshire Swash Regular"
      font.file := Font.fromURL(GoogleFont.`Berkshire Swash`.`regular`)
      font.size := 96.0
      fill := Paint.video("/sample.mp4")
      stroke := Stroke(Color.Black, lineWidth = 0.5)
      position.center := container.position.center
      position.top := pacifico.position.bottom + 20.0
    }

    container.children ++= Seq(pacifico, roboto, berkshire)
  }
}
