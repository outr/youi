package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.html._
import org.hyperscala.web._
import org.hyperscala.ui.module.ScriptLoader

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ScriptLoaderExample extends Example {
  require(ScriptLoader)
  require(Gritter)

  contents += new tag.P(content = "ScriptLoader provides the ability to dynamically load a .js file after the page has already fully loaded. Multiple calls will guarantee to loading order to avoid dependencies on previous JavaScript to be missing.")

  contents += new tag.Button(content = "Dynamically Load a Script") {
    clickEvent.onRealtime {
      case evt => {
        ScriptLoader.loadMultiple(this.webpage, List("/js/alert_message.js", "/js/alert_script.js"))
        ScriptLoader.whenFinished(this.webpage) {
          Gritter.add(this.webpage, "Finished", "All Scripts finished loading!")
        }
      }
    }
  }
}
