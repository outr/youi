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
  println(s"Creating new ScopedExample!")
  val loaded = Property[Int](default = Some(0))

  contents += new tag.P(content = "Scoped and ScopedReplacement allows a page with a specific Scope (Application in " +
    "this example) to have sections of the page Scoped differently. This means only one instance of a large page can " +
    "exist, but distinct aspects of the page can be loaded per request or per session allowing the server to remain " +
    "efficient while utilizing the full capabilities of Hyperscala.")

  contents += new tag.Div(id = "replaceable", content = "Original Content")

  connected[Webpage] {
    case webpage => {
      ScopedReplacement(webpage, Scope.Page, getById[tag.Div]("replaceable")) {
        case t => t.contents += s": ${loaded()}"
      }

      contents += new tag.Div(content = s"First Load: ${loaded()}")

      contents += Scoped(Scope.Request, webpage) {
        println(s"Scoped to request! ${loaded()}")
        loaded := loaded() + 1
        new tag.Div(content = s"Page Loaded ${loaded()} times!")
      }
      contents += Scoped(Scope.Session, webpage) {
        new tag.Div(content = s"Session Updated at ${loaded()}")
      }
      contents += Scoped(Scope.Application, webpage) {
        new tag.Div(content = s"Application Updated at ${loaded()}")
      }

      webpage.session {
        contents += new tag.Div(content = "New Session Added!")
      }
    }
  }
}