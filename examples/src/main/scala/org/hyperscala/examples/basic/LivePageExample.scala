package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.web.live.LivePage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LivePageExample extends LivePage {
  title := "Live Page Example"

  contents += "Hello World!"
}
