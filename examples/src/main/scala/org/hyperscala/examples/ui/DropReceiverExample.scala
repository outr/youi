package org.hyperscala.examples.ui


import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.Position
import org.hyperscala.ui.wrapped.DropReceiver
import org.hyperscala.jquery.Gritter
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DropReceiverExample extends Example {
  this.require(Gritter)

  contents += new tag.P {
    contents += "DropReceiver provides a convenient mechanism to receive drag-and-drop content of many types."
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

  val dropReceiver = DropReceiver(dropDiv)
  dropReceiver.dropped.on {
    case evt => Gritter.add(this.webpage, "Drop Received", s"Dropped: $evt")
  }
}