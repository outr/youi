package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.powerscala.Language
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.jquery.ui.{AutocompleteResult, Autocomplete}
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.examples.Example
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class AutoCompleteExample extends Example {
  Webpage().require(Realtime)
  Webpage().body.style.fontFamily = "Arial, sans-serif"

  contents += new tag.Div {
    style.paddingAll = 25.px
    val input = new Autocomplete {
      changeEvent := JavaScriptEvent()

      autocomplete.search := ((query: String) => {
        val v = query.toLowerCase
        Language.values.collect {
          case l if (l.name.toLowerCase.contains(v)) => l
        }.slice(0, 10).map(l => AutocompleteResult(l.name, l.name))
      })
      autocomplete.autoFocus := true
      autocomplete.multiple := true

      changeEvent.on {
        case evt => println("Input changed to: %s".format(value()))
      }
    }

    contents += input

    contents += new tag.Button(content = "Test") {
      clickEvent := JavaScriptEvent()

      clickEvent.on {
        case evt => {
          println("Clicked! - %s".format(input.autocomplete.selected()))
          input.value := "testing"
        }
      }
    }
  }
}
