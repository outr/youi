package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage
import org.hyperscala.jquery.Gritter
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class GritterExample extends Webpage {
  require(Gritter)

  body.contents += new tag.Button(content = "Show Message") {
    event.click := JavaScriptEvent()

    listeners.synchronous {
      case evt: ClickEvent => {
        Gritter.add("Test Title", "Example message that will disappear after a few seconds.", "http://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Go-home.svg/48px-Go-home.svg.png", time = 3000)
        Gritter.add("Test Title", "Example message that will disappear after a few seconds.", sticky = true)
      }
    }
  }
}
