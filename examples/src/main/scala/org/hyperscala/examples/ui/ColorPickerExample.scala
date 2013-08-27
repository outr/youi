package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.jquery.ColorPicker
import org.hyperscala.jquery.ColorPicker._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.Realtime
import org.powerscala.Color
import org.hyperscala.event.JavaScriptEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ColorPickerExample extends Example {
  Webpage().require(ColorPicker)
  Webpage().require(Realtime)

  val colorPicker = new tag.Input(id = "color")
  contents += colorPicker

  val picker = ColorPicker(colorPicker)
  picker.showNoneButton := true
  picker.color.change.on {
    case evt => println(s"color changed from ${evt.oldValue} to ${evt.newValue}")
  }

  contents += new tag.Button(id = "button", content = "Set to Red") {
    clickEvent.on {
      case evt => colorPicker.color := Color.Red
    }
    clickEvent := JavaScriptEvent()
  }
}
