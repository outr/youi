package org.hyperscala.examples.ui


import com.outr.net.http.session.Session
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.ui._
import org.hyperscala.realtime.Realtime
import org.hyperscala.selector.Selector
import org.hyperscala.ui.Bounding
import org.hyperscala.web._
import org.powerscala.Color

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BoundingExample extends Example {
  this.require(jQueryUI)
  this.require(Realtime)
  this.require(Bounding)

  contents += new tag.P {
    contents += "The Bounding module allows monitoring of a selector. A monitored element sends positional and size information back to the server as it changes. See this in action in the following draggable div below."
  }

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

  connected[Webpage[Session]] {
    case webpage => {
      Bounding.monitor(webpage, Selector.id(dragDiv), 0.2)
      Bounding.modified(webpage).on {
        case evt => synchronized {
          message.contents += new tag.Div(content = s"${evt.propertyName} changed from ${evt.oldValue} to ${evt.newValue}")
          if (message.contents.length > 10) {
            message.contents -= message.contents(0)   // Remove the first entry
          }
        }
      }
    }
  }
}