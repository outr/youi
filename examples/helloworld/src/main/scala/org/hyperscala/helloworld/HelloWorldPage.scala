package com.outr.webframework.helloworld

import com.outr.webframework.WebPage

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object HelloWorldPage extends WebPage("hello") {
  head.title := "Hello World Page"

  body.contents += "Hello World!"
}