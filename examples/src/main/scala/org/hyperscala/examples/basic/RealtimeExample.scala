package org.hyperscala.examples.basic

import org.hyperscala.web.Webpage
import org.hyperscala.html._
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.realtime.Realtime
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class RealtimeExample extends Webpage {
  require(Realtime)

  body.contents += new tag.Button(content = "Click Me!") {
    clickEvent := JavaScriptEvent()

    clickEvent.on {
      case evt => info("I've been clicked!")
    }
  }
}
