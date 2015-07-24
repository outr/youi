package org.hyperscala.examples.ui

import org.hyperscala.css.attributes.Position
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.realtime.{Realtime, RealtimeEvent}
import org.hyperscala.ui.module._
import org.hyperscala.web.Webpage
import org.powerscala.Color

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ComplianceExample extends Webpage with Example {
  require(Realtime)

  body.contents += new tag.P {
    contents += "Compliance offers features to assist with cross-browser compliance support for things like scaling content that works consistently and simply without having a development concern about what browser is being utilized."
  }

  def webpage = this

  val test = new tag.H2(id = "test", content = "Simple content within a div")
  val div = new tag.Div(id = "zoomable", content = test)
  div.style.position := Position.Relative
  div.style.width := 125.px
  div.style.height := 125.px
  div.style.backgroundColor := Color.Blue
  div.style.color := Color.White
  div.style.paddingAll(10.px)
  body.contents += div

  val div2 = new tag.Div(id = "zoomed", content = new tag.H2(id = "test", content = "Simple content within a div"))
  div2.style.position := Position.Relative
  div2.style.left := 350.px
  div2.style.top := -100.px
  div2.style.width := 125.px
  div2.style.height := 125.px
  div2.style.backgroundColor := Color.Blue
  div2.style.color := Color.White
  div2.style.paddingAll(10.px)
  div2.scale(this, 2.0)
  body.contents += div2

  body.contents += new tag.Button(content = "Zoom In") {
    style.position := Position.Relative
    style.zIndex := 10
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => div.scale(webpage, div.scale(webpage) + 0.5)
    }
  }
  body.contents += new tag.Button(content = "Zoom Normal") {
    style.position := Position.Relative
    style.zIndex := 10
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => div.scale(webpage, 1.0)
    }
  }
  body.contents += new tag.Button(content = "Zoom Out") {
    style.position := Position.Relative
    style.zIndex := 10
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => div.scale(webpage, div.scale(webpage) - 0.5)
    }
  }
}