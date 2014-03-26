package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.web.{Webpage, ScopedReplacement, Scoped, Scope}
import org.powerscala.property.Property
import org.hyperscala.html._
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ScopedExample extends Example {
  val loaded = Property[Int](default = Some(0))

  contents += new tag.Div(id = "replaceable", content = "Original Content")

  connected[Webpage[Session]] {
    case webpage => {
      ScopedReplacement(webpage.website, Scope.Page, getById[tag.Div]("replaceable")) {
        case t => t.contents += s" ${loaded()}"
      }

      contents += new tag.Div(content = s"First Load: ${loaded()}")

      contents += Scoped(Scope.Page, webpage.website) {
        loaded := loaded() + 1
        new tag.Div(content = s"Page Loaded ${loaded()} times!")
      }
      contents += Scoped(Scope.Session, webpage.website) {
        new tag.Div(content = s"Session Updated at ${loaded()}")
      }
      contents += Scoped(Scope.Application, webpage.website) {
        new tag.Div(content = s"Application Updated at ${loaded()}")
      }

      webpage.session {
        contents += new tag.Div(content = "New Session Added!")
      }
    }
  }
}