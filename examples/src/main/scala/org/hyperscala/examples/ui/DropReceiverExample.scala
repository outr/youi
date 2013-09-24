package org.hyperscala.examples.ui


import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.Position
import org.hyperscala.ui.wrapped.DropReceiver

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DropReceiverExample extends Example {
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
    case evt => println(s"Dropped: $evt")
  }
}