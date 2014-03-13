package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.examples.Example

import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.dsl._
import org.hyperscala.event.Key
import org.hyperscala.jquery.Gritter

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeDSLExample extends Example {
  page.require(Gritter)

  contents += new tag.P {
    contents += "Hyperscala provides a rich DSL for JavaScript and jQuery to be portrayed in Scala code that can be effectively converted to JavaScript in the browser. In the following example the jQuery DSL is used on the input to receive a notification on the server when the Escape key is pressed."
  }

  val input = new tag.Input(id = "my_input")
  contents += input

  $(input).keyUp(onKey(Key.Escape) {
    Gritter.add("Server Received", "Escape was pressed!")
  }).send()
}