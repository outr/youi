package org.hyperscala.examples.ui

import org.hyperscala.web.Webpage

import org.hyperscala.html._
import org.hyperscala.jquery.ui._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.Realtime
import org.powerscala.Color
import org.hyperscala.ui.Bounding
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BoundingExample extends Example {
  Webpage().require(jQueryUI.Latest)
  Webpage().require(Realtime)
  Webpage().require(Bounding)

  val dragDiv = new tag.Div(id = "draggable", content = "Drag me")
  dragDiv.style.width := 100.px
  dragDiv.style.height := 100.px
  dragDiv.style.paddingAll(10.px)
  dragDiv.style.marginAll(10.px)
  dragDiv.style.zIndex := 100
  dragDiv.style.backgroundColor := Color.LightBlue
  contents += dragDiv

  val draggableElement = Draggable(dragDiv)
  draggableElement.opacity := 0.5

  val message = new tag.Div(id = "message")
  contents += message

  Bounding.monitor(Selector.id(dragDiv), 0.2)
  Bounding.modified.on {
    case evt => synchronized {
      message.contents += new tag.Div(content = s"${evt.propertyName} changed from ${evt.oldValue} to ${evt.newValue}")
      if (message.contents.length > 10) {
        message.contents -= message.contents(0)   // Remove the first entry
      }
    }
  }
}