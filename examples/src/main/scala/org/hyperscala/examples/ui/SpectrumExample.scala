package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.jquery.Spectrum
import org.hyperscala.jquery.Spectrum._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SpectrumExample extends Example {
  this.require(Spectrum)
  this.require(Realtime)

  val colorPicker = new tag.Input(id = "color")
  contents += colorPicker

  val spectrum = Spectrum(colorPicker)
  spectrum.clickoutFiresChange := true
  spectrum.color.change.on {
    case evt => println(s"color changed from ${evt.oldValue} to ${evt.newValue}")
  }

  contents += new tag.Button(id = "button", content = "Set to Red") {
    clickEvent.on {
      case evt => colorPicker.color := Color.Red
    }
    clickEvent := RealtimeEvent()
  }

  contents += new tag.Button(id = "button2", content = "Toggle Show Input") {
    clickEvent.on {
      case evt => spectrum.showInput := !spectrum.showInput()
    }
    clickEvent := RealtimeEvent()
  }
}
