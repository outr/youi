package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage

import org.hyperscala.html._
import org.hyperscala.jquery.ui._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.Realtime
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DraggableExample extends Example {
  Webpage().require(jQueryUI.Latest)
  Webpage().require(Realtime)

  val dragDiv = new tag.Div(id = "draggable", content = "Drag me")
  dragDiv.style.width := 100.px
  dragDiv.style.height := 100.px
  dragDiv.style.backgroundColor := Color.LightBlue
  contents += dragDiv

  val draggableElement = Draggable(dragDiv)
  draggableElement.opacity := 0.5
  draggableElement.dragEvent.on {
    case evt => println("Dragged!!!")
  }
}