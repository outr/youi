package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.realtime.{RealtimeEvent, Realtime}
import language.reflectiveCalls
import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class RealtimeExample extends Example {
  page.require(Realtime)

  contents += new tag.Button(content = "Click Me!") {
    clickEvent := RealtimeEvent()

    clickEvent.on {
      case evt => info("I've been clicked!")
    }
  }
}
