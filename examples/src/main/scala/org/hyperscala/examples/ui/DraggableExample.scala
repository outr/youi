package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.ui._
import org.hyperscala.realtime.Realtime
import org.powerscala.Color

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DraggableExample extends Example {
  this.require(jQueryUI)
  this.require(Realtime)

  contents += new tag.P {
    contents += "Draggable provides a very simple wrapper around jQuery UI's Draggable functionality."
  }

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