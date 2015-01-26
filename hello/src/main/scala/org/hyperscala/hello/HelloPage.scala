package org.hyperscala.hello

import org.hyperscala.realtime._
import org.hyperscala.web.Webpage
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HelloPage extends Webpage(HelloSite) {
  require(Realtime)

  body.contents += new tag.H1(content = "Hello, World!")

  body.contents += new tag.Button {
    contents += "Click me!"
    clickEvent.onRealtime {
      case evt => println(s"Clicked!")
    }
  }
}
