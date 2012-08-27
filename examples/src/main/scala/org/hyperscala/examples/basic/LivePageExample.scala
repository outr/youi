package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.web.live.{LiveEvent, LivePage}
import org.powerscala.event.ActionEvent

import org.powerscala.property._
import org.powerscala.Color

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePageExample extends LivePage {
  title := "Live Page Example"

  var count = 0
  var reversed = false

  contents += new Button(content = "Test Button") {
    event.click := LiveEvent

    listeners.synchronous {
      case evt: ActionEvent => {
        contents.replaceWith("Test Button %s".format(count))
        style.color := Color.values.random
        if (count >= 10) {
          reversed = true
        } else if (count <= 0) {
          reversed = false
        }
        if (reversed) {
          count -= 1
          LivePageExample.this.contents -= LivePageExample.this.contents.last
        } else {
          count += 1
          LivePageExample.this.contents += new Div {
            contents += "Testing %s!".format(count)
          }
        }
      }
    }
  }
}
