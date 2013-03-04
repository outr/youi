package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.powerscala.Language
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.jquery.ui.{AutocompleteResult, Autocomplete}
import org.hyperscala.event.{ClickEvent, ChangeEvent, JavaScriptEvent}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class AutoCompleteExample extends Webpage {
  require(Realtime)
  body.style.fontFamily = "Arial, sans-serif"

  body.contents += new tag.Div {
    style.paddingAll = 25.px
    val input = new Autocomplete {
      event.change := JavaScriptEvent()

      autocomplete.search := ((query: String) => {
        val v = query.toLowerCase
        Language.values.collect {
          case l if (l.name().toLowerCase.contains(v)) => l
        }.slice(0, 10).map(l => AutocompleteResult(l.name(), l.name()))
      })
      autocomplete.autoFocus := true
      autocomplete.multiple := true

      listeners.synchronous {
        case evt: ChangeEvent => println("Input changed to: %s".format(value()))
      }
    }

    contents += input

    contents += new tag.Button(content = "Test") {
      event.click := JavaScriptEvent()

      listeners.synchronous {
        case evt: ClickEvent => {
          println("Clicked! - %s".format(input.autocomplete.selected()))
          input.value := "testing"
        }
      }
    }
  }
}
