package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.LineStyle
import org.hyperscala.ui.history.{HistoryEntry, History}
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HistoryExample extends Example {
  this.require(History)

  val div = new tag.Div(id = "myDiv") {
    style.width := 200.px
    style.height := 200.px
    style.backgroundColor := Color.Red
    style.borderColor := Color.Black
    style.borderWidth := 2.px
    style.borderStyle := LineStyle.Solid
  }
  contents += div
  contents += new tag.Button(id = "backgroundChanger", content = "Randomize Background Color") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => {
        val color = Color.random
        val entry = HistoryEntry.propertyChange("Background Color Modified", div.style.backgroundColor, div.style.backgroundColor(), color)
        History(this.webpage).add(entry, callRedo = true)
      }
    }
  }
  contents += new tag.Button(content = "Undo") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => History(this.webpage).undo()
    }
  }
  contents += new tag.Button(content = "Redo") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => History(this.webpage).redo()
    }
  }
}