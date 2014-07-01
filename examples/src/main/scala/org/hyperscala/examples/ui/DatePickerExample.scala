package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.jquery.ui.DatePicker

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DatePickerExample extends Example {
  this.require(Gritter)

  contents += new tag.P {
    contents += "Wrapper around jQuery UI's datepicker functionality. Simply click in the input field to see the date picker."
  }

  val input = new tag.Input(value = "01/02/2014")
  input.changeEvent := RealtimeEvent()
  input.value.change.on {
    case evt => Gritter.add(this.webpage, "Input Field Changed", s"Input field changed from ${evt.oldValue} to ${evt.newValue}.")
  }
  contents += input

  val datePicker = DatePicker(input)
  contents += new tag.Button(content = "Show Date Picker") {
    clickEvent.onRealtime {
      case evt => datePicker.show()
    }
  }
}
