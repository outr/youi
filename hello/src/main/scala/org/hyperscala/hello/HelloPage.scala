package org.hyperscala.hello

import org.hyperscala.web.site.Webpage
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HelloPage extends Webpage {
  body.contents += new tag.H1(content = "Hello, World!")
}
