package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.jquery.Spectrum
import org.hyperscala.jquery.Spectrum._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.hyperscala.realtime.Realtime
import org.powerscala.Color
import org.hyperscala.event.JavaScriptEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class SpectrumExample extends Example {
  Webpage().require(Spectrum)
  Webpage().require(Realtime)

  val colorPicker = new tag.Input(id = "color") {
    changeEvent := JavaScriptEvent()
    changeEvent.on {
      case evt => println(s"Color changed: ${value()}")
    }
  }
  contents += colorPicker

  Spectrum(colorPicker)

  val button = new tag.Button(id = "button", content = "Set to Red") {
    clickEvent.on {
      case evt => colorPicker.color := Color.Red
    }
    clickEvent := JavaScriptEvent()
  }
  contents += button
}
