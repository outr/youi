package org.hyperscala.examples.helloworld

import org.hyperscala.html._
import org.hyperscala.web.HTMLPage

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HelloWorldPage extends HTMLPage {
  head.contents += new tag.Link(rel = "stylesheet", href = "css/base.css")

  title := "Hello World Page"

  body.contents += "Hello World!"
}