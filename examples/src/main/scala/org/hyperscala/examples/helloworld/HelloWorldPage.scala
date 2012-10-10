package org.hyperscala.examples.helloworld

import org.hyperscala.html._
import org.hyperscala.web.HTMLPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HelloWorldPage extends HTMLPage {
  title := "Hello World Page"

  body.contents += "Hello World!"
}