package org.hyperscala.hello

import org.hyperscala.html._
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HelloPage extends Webpage {
  body.contents += new tag.H1(content = "Hello, World!")
}