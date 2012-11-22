package org.hyperscala.examples.helloworld

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HelloWorldPage extends Webpage {
  title := "Hello World Page"

  body.contents += "Hello World!"
}