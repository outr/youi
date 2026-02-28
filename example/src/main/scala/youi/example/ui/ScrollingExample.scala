package youi.example.ui

import rapid.Task
import youi._
import youi.component.support.{OverflowSupport, ScrollSupport}
import youi.component.types.{Display, Overflow}
import youi.component.{Container, TextView}
import youi.easing.Easing
import youi.event.EventSupport
import youi.example.ExampleApp
import youi.example.screen.UIExampleScreen
import youi.font.{GoogleFont, GoogleFontWeight}
import youi.material.{MDCButton, MaterialComponents}
import spice.net._
import youi.paint.Paint
import reactify._

import scala.concurrent.duration._

class ScrollingExample extends UIExampleScreen {
  override def title: String = "Scrolling Example"
  override def path: URLPath = path"/examples/scrolling.html"

  override def createUI(): Task[Unit] = {
    MaterialComponents.waitForLoaded().flatMap { _ =>
      GoogleFont.`Open Sans`.`regular`.load().map { fnt =>
        val scrollable = new Container with ScrollSupport with OverflowSupport {
          size.width := container.size.width
          size.height := container.size.height
          background := Paint.vertical(container.size.height).distributeColors(ExampleApp.bgColor(), Color.Black)
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

  class Box(val message: String, fnt: GoogleFontWeight, color: Color, w: Double, h: Double) extends Container with EventSupport {
    val text: Var[String] = Var(message)

    background := color
    display @= Display.InlineBlock
    children += new TextView {
      content := text
      font.weight @= fnt
      backgroundColor := ExampleApp.surfaceColor
      this.color := ExampleApp.textColor
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