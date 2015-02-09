package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.powerscala.Language
import org.hyperscala.web._
import org.hyperscala.realtime.{Realtime, RealtimeEvent}
import org.hyperscala.jquery.ui.{AutocompleteResult, Autocomplete}
import org.hyperscala.examples.Example
import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class AutoCompleteExample extends Example {
  this.require(Autocomplete)
  this.require(Gritter)
  connected[tag.Body] {
    case body => body.style.fontFamily := "Arial, sans-serif"
  }

  contents += new tag.P {
    contents += "Wrapper around jQuery UI's autocomplete functionality. Start typing a spoken language below to see it in action."
  }

  contents += new tag.Div {
    style.paddingTop := 25.px
    style.paddingBottom := 25.px
    style.paddingLeft := 25.px
    style.paddingRight := 25.px
    val input = new Autocomplete {
      changeEvent := RealtimeEvent()

      autocomplete.search := ((query: String) => {
        val v = query.toLowerCase
        Language.values.collect {
          case l if l.name.toLowerCase.contains(v) => l
        }.slice(0, 10).map(l => AutocompleteResult(l.name, l.name))
      })
      autocomplete.autoFocus := true

      changeEvent.on {
        case evt => Gritter.add(this.webpage, "Input Changed", "Input changed to: %s".format(value()))
      }
    }

    contents += input

    contents += new tag.Button(content = "Test") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => {
          Gritter.add(this.webpage, "Clicked!", input.autocomplete.selected().mkString(", "))
          input.value := "testing"
        }
      }
    }
  }
}
