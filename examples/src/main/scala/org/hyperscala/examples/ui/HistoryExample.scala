package org.hyperscala.examples.ui

import org.hyperscala.css.attributes.LineStyle
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.ui.history.{History, HistoryEntry}
import org.hyperscala.web._
import org.powerscala.Color

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HistoryExample extends Webpage with Example {
  require(History)

  val div = new tag.Div(id = "myDiv") {
    style.width := 200.px
    style.height := 200.px
    style.backgroundColor := Color.Red
    style.borderColor := Color.Black
    style.borderWidth := 2.px
    style.borderStyle := LineStyle.Solid
  }
  body.contents += div
  body.contents += new tag.Button(id = "backgroundChanger", content = "Randomize Background Color") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => {
        val color = Color.random
        val entry = HistoryEntry.propertyChange("Background Color Modified", div.style.backgroundColor, div.style.backgroundColor(), color)
        History(this.webpage).add(entry, callRedo = true)
      }
    }
  }
  body.contents += new tag.Button(content = "Undo") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => History(this.webpage).undo()
    }
  }
  body.contents += new tag.Button(content = "Redo") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => History(this.webpage).redo()
    }
  }
}