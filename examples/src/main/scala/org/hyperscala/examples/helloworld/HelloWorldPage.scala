package org.hyperscala.examples.helloworld

import org.hyperscala.html._
import org.hyperscala.web.site.Webpage

import org.powerscala.property._
import org.powerscala.Color
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}
import org.hyperscala.web.module.{HeadStyle, HeadScript}
import org.hyperscala.web.site.realtime.Realtime

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class HelloWorldPage extends Webpage {
  require(Realtime)
  require(HeadScript)
  require(HeadStyle)

  title := "Hello World Page"

  body.contents += "Hello World!"

  body.contents += new tag.Div(id = "test") {
    style.background.color := Color.Red
    style.color := Color.White
    style.font.weight := "bold"
    event.click := JavaScriptEvent()

    contents += "Test Content"

    listeners.synchronous {
      case evt: ClickEvent => println("I've been clicked!")
    }
  }
}