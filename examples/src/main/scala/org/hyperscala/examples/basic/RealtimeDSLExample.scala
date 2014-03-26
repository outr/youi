package org.hyperscala.examples.basic

import org.hyperscala.html._
import org.hyperscala.examples.Example

import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.dsl._
import org.hyperscala.event.Key
import org.hyperscala.jquery.Gritter
import org.hyperscala.web._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeDSLExample extends Example {
  this.require(Gritter)

  contents += new tag.P {
    contents += "Hyperscala provides a rich DSL for JavaScript and jQuery to be portrayed in Scala code that can be effectively converted to JavaScript in the browser. In the following example the jQuery DSL is used on the input to receive a notification on the server when the Escape key is pressed."
  }

  val input = new tag.Input(id = "my_input")
  contents += input

  connected[Webpage[Session]] {
    case webpage => {
      $(input).keyUp(onKey(webpage, Key.Escape) {
        Gritter.add(webpage, "Server Received", "Escape was pressed!")
      }).send(webpage)
    }
  }
}