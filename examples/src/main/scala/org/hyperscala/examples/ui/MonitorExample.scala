package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.ui.module.Monitor
import org.hyperscala.jquery.ui.Draggable
import org.hyperscala.web._

import org.hyperscala.css.attributes._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class MonitorExample extends Example {
  this.require(Monitor)

  val div = new tag.Div(id = "myDiv") {
    style.width := 200.px
    style.height := 200.px
    style.backgroundColor := Color.Red
    style.borderColor := Color.Black
    style.borderWidth := 2.px
    style.borderStyle := LineStyle.Solid
    style.position := Position.Relative
  }
  contents += div

  val message = new tag.Div(id = "message")
  contents += message

  Draggable(div)

  connected[Webpage[Session]] {
    case webpage => {
      Monitor.sync(webpage, div.style.left, 1.0)
      Monitor.sync(webpage, div.style.top, 1.0)
      div.style.left.and(div.style.top).change.on {
        case evt => {
          val content = new tag.I(content = s"Position: ${div.style.left()}/${div.style.top()}")
          message.contents.replaceWith(content)
        }
      }
    }
  }
}