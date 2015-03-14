package org.hyperscala.examples.basic

import org.hyperscala.html._
import attributes.ButtonType
import org.hyperscala.jquery.Gritter
import org.hyperscala.web._
import org.hyperscala.realtime._
import org.hyperscala.examples.Example
import com.outr.net.Method
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class RealtimeFormExample extends Example {
  this.require(Realtime)
  this.require(Gritter)

  connected[Webpage] {
    case webpage => webpage.connectForm()
  }

  contents += new tag.Form(method = Method.Get) {
    submitEvent.on {
      case evt => Gritter.add(this.webpage, "Form Submitted", s"Form submitted with '${input.value()}'.")
    }

    val input = new tag.Input(name = "field")
    contents += input
    contents += new tag.Button(buttonType = ButtonType.Submit, content = "Submit")
  }
}
