package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.selector.Selector
import org.hyperscala.web._
import org.hyperscala.realtime._
import org.hyperscala.examples.Example
import org.hyperscala.jquery.ui.ProgressBar
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ProgressBarExample extends Example {
  this.require(Realtime)

  val placeholder = new tag.Div(id = "progressBar")
  val progressBar = ProgressBar(placeholder)

  contents += placeholder
  contents += new tag.Button(content = "Random Value - Determinate") {
    clickEvent.onRealtime {
      case evt => progressBar.value := Some(Math.round(Math.random() * 100.0).toInt)
    }
  }
  contents += new tag.Button(content = "Indeterminate") {
    clickEvent.onRealtime {
      case evt => progressBar.value := None
    }
  }
  contents += new tag.Button(content = "Random Color") {
    clickEvent.onRealtime {
      case evt => this.webpage.head.selector(Selector("#progressBar .ui-progressbar-value")).background := Color.random.hex.rgb
    }
  }
}
