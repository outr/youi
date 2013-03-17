package org.hyperscala.examples.basic

import org.hyperscala.html._
import attributes.ButtonType
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.event.SubmitEvent
import org.hyperscala.examples.Example

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class RealtimeFormExample extends Example {
  Webpage().require(Realtime)
  Realtime.connectForm()

  contents += new tag.Form(method = "get") {
    listeners.synchronous {
      case evt: SubmitEvent => println("Form submitted with '%s'.".format(input.value()))
    }

    val input = new tag.Input(name = "name")
    contents += input
    contents += new tag.Button(buttonType = ButtonType.Submit, content = "Submit")
  }
}
