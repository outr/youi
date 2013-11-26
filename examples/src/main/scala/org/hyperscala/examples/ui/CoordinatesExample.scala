package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.{Vertical, Horizontal, Position}
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.ui._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CoordinatesExample extends Example {
  page.require(WindowSize)
  page.require(Coordinates)

  val div = new tag.Div(id = "div1", content = new tag.H2(id = "content1", content = "Simple content within a div"))
  div.style.position := Position.Relative
  div.style.left := 0.px
  div.style.top := 0.px
  div.style.width := 125.px
  div.style.height := 125.px
  div.style.backgroundColor := Color.Blue
  div.style.color := Color.White
  div.style.paddingAll(10.px)
  contents += div

  val div2 = new tag.Div(id = "div2", content = new tag.H2(id = "content2", content = "Simple content within a div"))
  div2.style.position := Position.Relative
  div2.style.left := 600.px
  div2.style.top := -150.px
  div2.style.width := 125.px
  div2.style.height := 125.px
  div2.style.backgroundColor := Color.Blue
  div2.style.color := Color.White
  div2.style.paddingAll(10.px)
  contents += div2

  val converter = new AbsoluteAdjustedConverter(
    x => x - (WindowSize.width() / 2.0),
    y => y - (WindowSize.height() / 2.0),
    x => x + (WindowSize.width() / 2.0),
    y => y + (WindowSize.height() / 2.0)
  )
  val coordinates = new Coordinates(converter)
  val divc = coordinates(div)
  val divc2 = coordinates(div2)
  divc.horizontal := Horizontal.Center
  divc.vertical := Vertical.Middle
  divc.x.change and divc.y.change on {
    case evt => updateMessage()
  }

  contents += new tag.Button(content = "Center Div1") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => {
        divc.x := 0.0
        divc.y := 0.0
        divc.manageX := true
        divc.manageY := true
      }
    }
  }

  val content = new tag.Div(id = "message")
  contents += content

  def updateMessage() = {
    setMessage(s"Div: ${divc.x()} x ${divc.y()}<br/>Div2: ${divc2.x()} x ${divc2.y()}")
  }

  def setMessage(message: String) = content.contents.replaceWith(new tag.H2(content = message))
}