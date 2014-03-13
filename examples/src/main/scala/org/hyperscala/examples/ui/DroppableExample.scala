package org.hyperscala.examples.ui

import org.hyperscala.web.Webpage

import org.hyperscala.html._
import org.hyperscala.jquery.ui._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.Realtime
import org.powerscala.Color
import org.hyperscala.css.attributes.Position

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DroppableExample extends Example {
  Webpage().require(jQueryUI.Latest)
  Webpage().require(Realtime)

  contents += new tag.P {
    contents += "Droppable utilizes jQuery UI's Droppable functionality to support simple drag-and-drop."
  }

  val dropDiv = new tag.Div(id = "droppable", content = "Drop here")
  dropDiv.style.position := Position.Relative
  dropDiv.style.left := 250.px
  dropDiv.style.top := 0.px
  dropDiv.style.width := 125.px
  dropDiv.style.height := 125.px
  dropDiv.style.backgroundColor := Color.Blue
  dropDiv.style.color := Color.White
  dropDiv.style.paddingAll(10.px)
  contents += dropDiv

  val dragDiv = new tag.Div(id = "draggable", content = "Drag me")
  dragDiv.style.width := 100.px
  dragDiv.style.height := 100.px
  dragDiv.style.backgroundColor := Color.LightBlue
  contents += dragDiv

  val draggableElement = Draggable(dragDiv)
  draggableElement.opacity := 0.5

  val droppableElement = Droppable(dropDiv)
  droppableElement.dropEvent.on {
    case evt => println(s"Droppped ${evt.draggable.wrapped.identity} at ${evt.offsetLeft}x${evt.offsetTop}")
  }
}