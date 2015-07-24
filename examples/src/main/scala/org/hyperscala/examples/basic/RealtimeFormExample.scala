package org.hyperscala.examples.basic

import com.outr.net.Method
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.html.attributes.ButtonType
import org.hyperscala.jquery.Gritter
import org.hyperscala.realtime._
import org.hyperscala.web._

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class RealtimeFormExample extends Webpage with Example {
  require(Realtime)
  require(Gritter)

  this.connectForm()

  body.contents += new tag.Form(method = Method.Get) {
    submitEvent.on {
      case evt => Gritter.add(this.webpage, "Form Submitted", s"Form submitted with '${input.value()}'.")
    }

    val input = new tag.Input(name = "field")
    contents += input
    contents += new tag.Button(buttonType = ButtonType.Submit, content = "Submit")
  }
}
