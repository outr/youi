package io.youi.example.ui

import rapid.Task
import io.youi._
import io.youi.component.support.BorderSupport
import io.youi.component.types.{Border, BorderStyle, Cursor, PointerEvents, PositionType, WhiteSpace}
import io.youi.component.{Container, TextView}
import io.youi.event.EventSupport
import io.youi.example.screen.UIExampleScreen
import io.youi.font.GoogleFont
import spice.net._

class MeasuredExample extends UIExampleScreen {
  override def title: String = "Measured Example"
  override def path: URLPath = path"/examples/measured.html"

  override def createUI(): Task[Unit] = GoogleFont.`Lobster`.`regular`.load().map { fnt =>
    val textView = new TextView {
      content @= "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
      font.weight @= fnt
      font.size   @= 20.0
      color       @= Color.DarkBlue
      position.`type` @= PositionType.Absolute
      size.width  @= 600.0
      whiteSpace  @= WhiteSpace.Normal
      position.center := container.size.center
      position.middle := container.size.middle
    }

    val heading = new TextView with EventSupport {
      content @= "Heading Test (click to grow)"
      font.weight @= fnt
      font.size   @= 36.0
      color       @= Color.DarkBlue
      cursor      @= Cursor.Pointer
      position.`type` @= PositionType.Absolute
      position.center := container.size.center
      position.bottom := textView.position.top - 10.0
      event.click.on {
        font.size @= font.size() + 5.0
      }
    }

    val textOutline = new Container with BorderSupport {
      pointerEvents @= PointerEvents.None
      border @= Border(2.0, BorderStyle.Dotted, Color.DarkRed)
      position.`type` @= PositionType.Absolute
      size.width  := textView.measured.width
      size.height := textView.measured.height
      position.center := container.size.center
      position.middle := container.size.middle
    }

    val headingOutline = new Container with BorderSupport {
      pointerEvents @= PointerEvents.None
      border @= Border(2.0, BorderStyle.Dotted, Color.DarkGreen)
      position.`type` @= PositionType.Absolute
      position.left := heading.position.left
      position.top  := heading.position.top
      size.width    := heading.measured.width
      size.height   := heading.measured.height
    }

    container.children ++= Seq(textView, heading, textOutline, headingOutline)
  }
}
