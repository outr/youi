package org.hyperscala.examples.basic

import org.hyperscala.html._
import attributes.ButtonType
import org.hyperscala.web.site.Webpage
import org.hyperscala.realtime.Realtime
import org.hyperscala.event.SubmitEvent

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class RealtimeFormExample extends Webpage {
  require(Realtime)
  Realtime.connectForm()

  body.contents += new tag.Form(method = "get") {
    listeners.synchronous {
      case evt: SubmitEvent => println("Form submitted with '%s'.".format(input.value()))
    }

    val input = new tag.Input(name = "name")
    contents += input
    contents += new tag.Button(buttonType = ButtonType.Submit, content = "Submit")
  }
}
