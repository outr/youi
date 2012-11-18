package org.hyperscala.examples.ui

import org.hyperscala.ui.widgets.AutoCompleteInput
import org.hyperscala.html._
import org.powerscala.property._
import org.powerscala.Language
import org.powerscala.property.event.PropertyChangeEvent
import org.hyperscala.web.site.realtime.RealtimeWebpage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class AutoCompleteExample extends RealtimeWebpage {
  body.style.font.family := "Arial, sans-serif"

  body.contents += new tag.Div {
    style.padding.all := 25.px

    val input = new AutoCompleteInput[Language]("language", Language.English) {
      def complete(value: String) = {
        val v = value.toLowerCase
        Language.values.collect {
          case l if (l.name().toLowerCase.contains(v)) => l
        }.slice(0, 10)
      }
    }
    input.property.listeners.synchronous {
      case evt: PropertyChangeEvent => println("OldValue: %s, NewValue: %s".format(evt.oldValue, evt.newValue))
    }
    contents += input

    contents += "Hello world! Goodbye world! Blah blah blah!"
  }
}
