package org.hyperscala.examples.basic

import org.hyperscala.html._
import attributes.ButtonType
import org.hyperscala.web._
import org.hyperscala.realtime.Realtime
import org.hyperscala.examples.Example
import com.outr.net.Method
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class RealtimeFormExample extends Example {
  this.require(Realtime)
  connected[Webpage[Session]] {
    case webpage => Realtime.connectForm(webpage)
  }
//  Realtime.connectPost()

  contents += new tag.Form(method = Method.Get) {
    submitEvent.on {
      case evt => println("Form submitted with '%s'.".format(input.value()))
    }

    val input = new tag.Input(name = "field")
    contents += input
    contents += new tag.Button(buttonType = ButtonType.Submit, content = "Submit")
  }
}
