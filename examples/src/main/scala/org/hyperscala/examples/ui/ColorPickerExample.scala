package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.ColorPicker
import org.hyperscala.jquery.ColorPicker._
import org.hyperscala.realtime.{Realtime, RealtimeEvent}
import org.hyperscala.web._
import org.powerscala.Color

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ColorPickerExample extends Webpage with Example {
  require(ColorPicker)
  require(Realtime)

  body.contents += new tag.P {
    contents += "ColorPicker module wraps around an input field to provide an implementation of jQuery color picking."
  }

  val colorPicker = new tag.Input(id = "color")
  body.contents += colorPicker

  val picker = ColorPicker(colorPicker)
  picker.showNoneButton := true
  picker.color.change.on {
    case evt => println(s"color changed from ${evt.oldValue} to ${evt.newValue}")
  }

  body.contents += new tag.Button(id = "button", content = "Set to Red") {
    clickEvent.on {
      case evt => colorPicker.color := Color.Red
    }
    clickEvent := RealtimeEvent()
  }
  body.contents += new tag.Button(id = "button2", content = "Set to null") {
    clickEvent.on {
      case evt => colorPicker.color := null
    }
    clickEvent := RealtimeEvent()
  }
}
