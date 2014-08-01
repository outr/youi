package org.hyperscala.examples.basic

import java.util.Calendar

import org.hyperscala.Page
import org.hyperscala.examples.Example
import org.hyperscala.realtime.Realtime
import org.hyperscala.web._

import org.hyperscala.html._
import org.hyperscala.realtime._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeDateExample extends Example {
  this.require(Realtime)

  connected[Page] {
    case page => page.intercept.update.on {
      case delta => {
        println(s"Update! $delta")
        updateTime()
      }
    }
  }

  val div = new tag.Div
  contents += div

  contents += new tag.Button(content = "Push Me") {
    clickEvent.onRealtime {
      case evt => updateTime()
    }
  }

  def updateTime() = {
    div.contents.replaceWith(new tag.H1(content = Calendar.getInstance().getTime.toString))
  }
}