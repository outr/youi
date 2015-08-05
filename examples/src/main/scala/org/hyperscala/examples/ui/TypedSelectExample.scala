package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.realtime.{Realtime, RealtimeEvent}
import org.hyperscala.ui.wrapped.TypedSelect
import org.hyperscala.web.Webpage
import org.powerscala.Country

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TypedSelectExample extends Webpage with Example {
  require(Realtime)

  val select = new tag.Select(id = "select")
  val typedSelect = TypedSelect[Country](select, null :: Country.values.toList) {
    case c => if (c == null) "--- Select a Country ---" else c.fullName
  }
  typedSelect.selected.change.on {
    case evt => println(s"Selected changed from: ${evt.oldValue} to: ${evt.newValue}")
  }

  body.contents += select

  body.contents += new tag.Br

  body.contents += new tag.Button(content = "Select USA") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => typedSelect.selected := Country.US
    }
  }
  body.contents += new tag.Button(content = "Repopulate") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => typedSelect.options = null :: Country.values.toList
    }
  }
}