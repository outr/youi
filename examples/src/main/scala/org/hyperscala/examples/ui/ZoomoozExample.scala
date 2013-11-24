package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.Position
import org.hyperscala.jquery.dsl._
import org.hyperscala.jquery._
import org.hyperscala.realtime.dsl._
import org.hyperscala.realtime.RealtimeEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ZoomoozExample extends Example {
  page.require(Zoomooz)

  val test = new tag.H2(id = "test", content = "Simple content within a div")
  val div = new tag.Div(id = "zoomable", content = test)
  div.style.position := Position.Relative
  div.style.left := 250.px
  div.style.top := 0.px
  div.style.width := 125.px
  div.style.height := 125.px
  div.style.backgroundColor := Color.Blue
  div.style.color := Color.White
  div.style.paddingAll(10.px)
  contents += div

  contents += new tag.Button(content = "Zoom In") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => $(test).zoomTo(targetSize = 1.5, duration = 1.0, root = $(div)).send()
    }
  }
  contents += new tag.Button(content = "Zoom Normal") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => $(div).zoomTo(targetSize = 1.0, duration = 1.0, root = $(div)).send()
    }
  }
}