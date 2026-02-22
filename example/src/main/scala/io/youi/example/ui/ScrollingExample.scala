package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.support.{OverflowSupport, ScrollSupport, SizeSupport}
import io.youi.component.types.{Display, Overflow}
import io.youi.component.{Container, TextView}
import io.youi.easing.Easing
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.{GoogleFont, GoogleFontWeight}
import io.youi.material.{MDCButton, MaterialComponents}
import spice.net._
import io.youi.paint.Paint
import reactify._

import scala.concurrent.duration._

class ScrollingExample extends UIExampleScreen {
  override def title: String = "Scrolling Example"
  override def path: URLPath = path"/examples/scrolling.html"

  override def createUI(): Task[Unit] = {
    MaterialComponents.waitForLoaded().flatMap { _ =>
      GoogleFont.`Open Sans`.`regular`.load().map { fnt =>
        val scrollable = new Container with SizeSupport with ScrollSupport with OverflowSupport {
          size.width := container.size.width
          size.height := container.size.height
          background := Paint.vertical(container.size.height).distributeColors(Color.White, Color.Black)
          overflow @= Overflow.Auto
        }

        val button = new MDCButton("Scroll to 50") with EventSupport
        button.event.click.on {
          val box = scrollable.children()(51).asInstanceOf[Box]
          box.text @= "SELECTED"
          scrollable.scroll.to(box, in = 500.millis, easing = Easing.elasticOut, horizontal = true, alignBottom = true)
        }
        scrollable.children += button
        scrollable.children += dom.create.br

        Color.all.take(100).foreach { color =>
          val box = Box(color, fnt)
          scrollable.children += box
          box
        }

        container.children += scrollable
      }
    }
  }

  class Box(val message: String, fnt: GoogleFontWeight, color: Color, w: Double, h: Double) extends Container with SizeSupport with EventSupport {
    val text: Var[String] = Var(message)

    background := color
    display @= Display.InlineBlock
    children += new TextView {
      content := text
      font.weight @= fnt
      backgroundColor := Color.White
      this.color := Color.Black
      font.size := 24.0
    }
    size.width := w
    size.height := h

    event.pointer.overState.attach {
      case true => background := Color.Red
      case false => background := color
    }

    override def toString: String = message
  }

  object Box {
    def apply(color: Color, font: GoogleFontWeight, width: Double = 200.0, height: Double = 200.0): Box = {
      new Box(s"Color: ${color.toHex}", font, color, width, height)
    }
  }
}