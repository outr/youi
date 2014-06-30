package org.hyperscala.examples.ui

import org.hyperscala.html._

import org.hyperscala.examples.Example
import org.hyperscala.web._
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import org.hyperscala.ui.widgets.Select2
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.jquery.Gritter
import org.powerscala.Language

import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Select2Example extends Example {
  this.require(Realtime)
  this.require(Gritter)

  val select = new tag.Select(id = "test") {
    contents += new tag.Option(value = "apple", content = "Apple")
    contents += new tag.Option(value = "orange", content = "Orange")
    contents += new tag.Option(value = "banana", content = "Banana")

    changeEvent := RealtimeEvent()
    value.change.on {
      case evt => Gritter.add(this.webpage, "Selection Changed", s"Value changed to ${value()}.")
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
  contents += new tag.Button(content = "Select Banana") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => select2.value := "banana"
    }
  }

  contents += new tag.Br
  contents += new tag.Br

  val multiSelect = new tag.Select(id = "testMultiple", multiple = true) {
    style.minWidth := 120.px
    Language.values.foreach(l => contents += new tag.Option(value = l.name, content = l.label))

    changeEvent := RealtimeEvent()
    selected.change.on {
      case evt => Gritter.add(this.webpage, "Multiple Selection Changed", s"Values changed to ${evt.newValue.mkString(", ")}.")
    }
  }
  contents += multiSelect
  contents += new tag.Button(content = "Select English and French") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => select2Multi.values := List(Language.English.name, Language.French.name)
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

  val select2 = Select2(select)
  select2.formatResult := formatter
  select2.formatSelection := formatter
  select2.escapeMarkup := Select2.DontEscapeMarkup

  val select2Multi = Select2(multiSelect)
}
