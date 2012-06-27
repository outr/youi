package org.hyperscala.helloworld

import org.hyperscala.WebPage

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object HelloWorldPage extends WebPage("hello") {
  head.title := "Hello World Page"

  body.contents += "Hello World!"
}