package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.{Vertical, Horizontal, Position}
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.ui._
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CoordinatesExample extends Example {
  page.require(WindowSize)
  page.require(Coordinates)

  val div = new tag.Div(id = "div1", content = new tag.H2(id = "content1", content = "Centered Content"))
  div.style.position := Position.Absolute
  div.style.left := 0.px
  div.style.top := 0.px
  div.style.width := 125.px
  div.style.height := 125.px
  div.style.backgroundColor := Color.Blue
  div.style.color := Color.White
  div.style.paddingAll(10.px)
  contents += div

  val div2 = new tag.Div(id = "div2", content = new tag.H2(id = "content2", content = "Right Side of Page"))
  div2.style.position := Position.Absolute
  div2.style.width := 125.px
  div2.style.height := 125.px
  div2.style.backgroundColor := Color.Blue
  div2.style.color := Color.White
  div2.style.paddingAll(10.px)
  contents += div2

  val centerDiv = new tag.Div(id = "centerDiv") {
    style.position := Position.Absolute
    style.width := 6.px
    style.height := 6.px
    style.zIndex := 100
    style.backgroundColor := Color.Red
    WindowSized.resized(new JavaScriptString(
      """
        |var centerDiv = $('#centerDiv');
        |centerDiv.css('left', (windowWidth - centerDiv.width()) / 2);
        |centerDiv.css('top', (windowHeight - centerDiv.height()) / 2);
      """.stripMargin))
  }
  contents += centerDiv

  val converter = new AdjustedConverter(
    x => x - (WindowSize.width() / 2.0),
    y => y - (WindowSize.height() / 2.0),
    x => x + (WindowSize.width() / 2.0),
    y => y + (WindowSize.height() / 2.0)
  )
  val coordinates = new Coordinates(converter)

  val divc = coordinates(div)
  divc.horizontal := Horizontal.Center
  divc.vertical := Vertical.Middle
  divc.x := 0.0
  divc.y := 0.0
  divc.manageX := true
  divc.manageY := true

  val divc2 = coordinates(div2)
  divc2.horizontal := Horizontal.Right
  divc2.x := 480.0

  contents += new tag.Button(content = "Horizontal.Left") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => divc.horizontal := Horizontal.Left
    }
  }
  contents += new tag.Button(content = "Horizontal.Center") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => divc.horizontal := Horizontal.Center
    }
  }
  contents += new tag.Button(content = "Horizontal.Right") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => divc.horizontal := Horizontal.Right
    }
  }
}