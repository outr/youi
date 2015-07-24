package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.web.{Scope, Scoped, ScopedReplacement, Webpage}
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ScopedExample extends Webpage with Example {
  println(s"Creating new ScopedExample!")
  val loaded = Property[Int](default = Some(0))

  body.contents += new tag.P(content = "Scoped and ScopedReplacement allows a page with a specific Scope (Application in " +
    "this example) to have sections of the page Scoped differently. This means only one instance of a large page can " +
    "exist, but distinct aspects of the page can be loaded per request or per session allowing the server to remain " +
    "efficient while utilizing the full capabilities of Hyperscala.")

  body.contents += new tag.Div(id = "replaceable", content = "Original Content")

  ScopedReplacement(this, Scope.Page, getById[tag.Div]("replaceable")) {
    case t => t.contents += s": ${loaded()}"
  }

  body.contents += new tag.Div(content = s"First Load: ${loaded()}")

  body.contents += Scoped(Scope.Request, this) {
    println(s"Scoped to request! ${loaded()}")
    loaded := loaded() + 1
    new tag.Div(content = s"Page Loaded ${loaded()} times!")
  }
  body.contents += Scoped(Scope.Session, this) {
    new tag.Div(content = s"Session Updated at ${loaded()}")
  }
  body.contents += Scoped(Scope.Application, this) {
    new tag.Div(content = s"Application Updated at ${loaded()}")
  }

  session {
    body.contents += new tag.Div(content = "New Session Added!")
  }
}