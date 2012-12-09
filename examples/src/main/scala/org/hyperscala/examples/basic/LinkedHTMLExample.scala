package org.hyperscala.examples.basic

import org.hyperscala.html._

import org.hyperscala.web.site.Webpage
import org.hyperscala.web.LinkedHTML
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class LinkedHTMLExample extends Webpage {
  body.contents += new LinkedExample
}

class LinkedExample extends LinkedHTML(getClass.getClassLoader.getResource("linked.html")) {
  val nameInput = link(new tag.Input(name = "name", id = "i1"))
  val ageInput = link(new tag.Input(name = "age", id = "i2"))
  val button = link(new tag.Button(id = "b1", content = "Changed"))

  def modify() = {
    nameInput.value := "John Doe"
    ageInput.value := "123"
    button.event.click := JavaScriptEvent()

    button.listeners.synchronous {
      case evt: ClickEvent => println("Clicked!")
    }
  }
}