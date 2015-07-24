package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.jquery.ui.{Autocomplete, AutocompleteResult}
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.web._
import org.powerscala.Language

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class AutoCompleteExample extends Webpage with Example {
  require(Autocomplete)
  require(Gritter)
  connected[tag.Body] {
    case body => body.style.fontFamily := "Arial, sans-serif"
  }

  body.contents += new tag.P {
    contents += "Wrapper around jQuery UI's autocomplete functionality. Start typing a spoken language (like 'English') below to see it in action."
  }

  body.contents += new tag.Div {
    style.paddingTop := 25.px
    style.paddingBottom := 25.px
    style.paddingLeft := 25.px
    style.paddingRight := 25.px
    val input = new Autocomplete {
      changeEvent := RealtimeEvent()

      autocomplete.search := ((query: String) => {
        val v = query.toLowerCase
        Language.values.collect {
          case l if l.label.toLowerCase.contains(v) => l
        }.slice(0, 10).map(l => AutocompleteResult(l.label, l.label))
      })
      autocomplete.autoFocus := true

      autocomplete.selected.change.on {
        case evt => Gritter.add(this.webpage, "Selection Changed", s"Selection changed from ${evt.oldValue} to ${evt.newValue}.")
      }
    }

    contents += input

    contents += new tag.Button(content = "Set to Testing") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => input.value := "Testing"
      }
    }
  }
}
