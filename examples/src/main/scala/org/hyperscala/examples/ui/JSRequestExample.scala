package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.css.attributes.Position
import org.powerscala.Color
import org.hyperscala.realtime.JSRequest
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.dsl._
import org.hyperscala.jquery.Gritter
import org.hyperscala.web._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
class JSRequestExample extends Example {
  this.require(JSRequest)
  this.require(Gritter)

  val div = new tag.Div(id = "myDiv", content = "Positioned Element") {
    style.width := 100.px
    style.height := 100.px
    style.paddingAll(5.px)
    style.position := Position.Absolute
    style.backgroundColor := Color.LightBlue
  }
  contents += div

  connected[Webpage[Session]] {
    case webpage => {
      JSRequest.send(webpage, $(div).offset().left, $(div).offset().top) {
        case response => Gritter.add(webpage, "Response Received", s"Response received: $response", sticky = true)
      }
    }
  }
}
