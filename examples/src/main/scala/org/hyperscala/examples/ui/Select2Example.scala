package org.hyperscala.examples.ui

import org.hyperscala.html._

import org.hyperscala.examples.Example
import org.hyperscala.web.Webpage
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import org.hyperscala.ui.widgets.Select2
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.Gritter

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Select2Example extends Example {
  Webpage().require(Realtime)
  Webpage().require(Gritter)

  val select = new tag.Select(id = "test") {
    contents += new tag.Option(value = "apple", content = "Apple")
    contents += new tag.Option(value = "orange", content = "Orange")
    contents += new tag.Option(value = "banana", content = "Banana")

    changeEvent := RealtimeEvent()
    value.change.on {
      case evt => Gritter.add("Selection Changed", s"Value changed to ${value()}")
    }
  }
  contents += select

  contents += new tag.Button(content = "Add Grape") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => {
        select.contents += new tag.Option(value = "grape", content = "Grape")
        removeFromParent()
      }
    }
  }

  // Custom formatting to hide 'Orange' and show 'Fruit: ' in front of everything else.
  val formatter = JavaScriptString(
    """
      |function (option, container) {
      | if (option.text == 'Orange') {
      |   container.hide()
      | } else {
      |   container.show()
      |   return '<span><em>Fruit: </em>' + option.text + '</span>';
      | }
      |}
    """.stripMargin)
  Select2(select, formatResult = Some(formatter), formatSelection = Some(formatter), escapeMarkup = Some(Select2.DontEscapeMarkup))
}
