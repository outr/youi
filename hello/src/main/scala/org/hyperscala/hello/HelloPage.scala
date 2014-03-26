package org.hyperscala.hello

import org.hyperscala.web.Webpage
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HelloPage extends Webpage(HelloSite) {
  body.contents += new tag.H1(content = "Hello, World!")
}
