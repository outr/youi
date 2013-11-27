package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.{Vertical, Horizontal, Position}
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.ui._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CoordinatesExample extends Example {
  page.require(WindowSize)

  val div = new tag.Div(id = "div1", clazz = List("bounding"), content = new tag.H2(id = "content1", content = "Centered Content"))
  div.style.position := Position.Relative
  div.style.left := 0.px
  div.style.top := 0.px
  div.style.width := 125.px
  div.style.height := 125.px
  div.style.backgroundColor := Color.Blue
  div.style.color := Color.White
  div.style.paddingAll(10.px)
  contents += div

  val div2 = new tag.Div(id = "div2", clazz = List("bounding"), content = new tag.H2(id = "content2", content = "Right Side of Page"))
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

  Bounding.monitor(Selector.clazz("bounding"), 0.5)   // Monitor bounding div1 and div2

  val coordinates = new Coordinates {
    def coordinateX(ct: CoordinatesTag) = ct.b.absoluteX() - (WindowSize.width() / 2.0)

    def coordinateY(ct: CoordinatesTag) = ct.b.absoluteY() - (WindowSize.height() / 2.0)

    def localizeX(ct: CoordinatesTag) = ct.x() + (WindowSize.width() / 2.0)

    def localizeY(ct: CoordinatesTag) = ct.y() + (WindowSize.height() / 2.0)

    override protected def create(t: HTMLTag) = {
      val ct = super.create(t)
      WindowSize.width.change and WindowSize.height.change on {   // This coordinate system depends on WindowSize as well
        case evt => ct.update()
      }
      ct
    }
  }

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