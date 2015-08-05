package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.Gritter
import org.hyperscala.jquery.ui.DatePicker
import org.hyperscala.realtime._
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DatePickerExample extends Webpage with Example {
  require(Gritter)

  body.contents += new tag.P {
    contents += "Wrapper around jQuery UI's datepicker functionality. Simply click in the input field to see the date picker."
  }

  val input = new tag.Input(value = "01/02/2014")
  input.changeEvent := RealtimeEvent()
  input.value.change.on {
    case evt => Gritter.add(this, "Input Field Changed", s"Input field changed from ${evt.oldValue} to ${evt.newValue}.")
  }
  body.contents += input

  val datePicker = DatePicker(input)
  body.contents += new tag.Button(content = "Show Date Picker") {
    clickEvent.onRealtime {
      case evt => datePicker.show()
    }
  }
}
