package io.youi.example.ui

import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.mixins.{ScrollBar, ScrollSupport}
import io.youi.component.{Container, TextView}
import io.youi.font.{GoogleFont, OpenTypeFont}
import io.youi.layout.VerticalLayout
import io.youi.paint.{Paint, Stroke}
import reactify._

import scala.concurrent.Future

object ScrollingExample extends UIExampleScreen with UIScreen {
  override def name: String = "Scrolling"
  override def path: String = "/examples/scrolling.html"

  override def createUI(): Future[Unit] = {
    val scrollable = new Container with ScrollSupport {
      size.width := ui.width
      size.height := ui.height
      background := Paint.vertical(container.size.height).distributeColors(Color.White, Color.Black)
      layoutManager := new VerticalLayout(25.0)
      scroll.vertical.bar := ScrollBar.simple(stroke = Stroke(Color.Black), fill = Color.LightBlue)
    }

    Color.all.take(20).foreach { color =>
      val box = Box(color)
      scrollable.children += box
      box
    }

    container.children += scrollable
    Future.successful(())
  }

  class Box(val message: String, color: Color, w: Double, h: Double) extends Container {
    background := color
    children += new TextView {
      value := message
      OpenTypeFont.fromURL(GoogleFont.`Open Sans`.`regular`).foreach { fnt =>
        font.file := fnt
      }
      fill := Color.White
      stroke := Stroke(Color.Black)
      font.size := 48.0
      position.left := 20.0
      position.top := 10.0
    }
    position.left := 25.0
    size.width := w
    size.height := h

    event.pointer.overState.attach {
      case true => background := Color.Red
      case false => background := color
    }
    event.click.on {
      if (size.height() == h) {
        size.height := h * 1.25
      } else {
        size.height := h
      }
    }

    override def toString: String = message
  }

  object Box {
    def apply(color: Color, width: Double = 500.0, height: Double = 200.0): Box = {
      new Box(Color.name(color).get, color, width, height)
    }
  }
}