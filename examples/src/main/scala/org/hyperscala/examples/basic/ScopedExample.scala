package org.hyperscala.examples.basic

import org.hyperscala.examples.Example
import org.hyperscala.web.{ScopedReplacement, Scoped, Scope}
import org.powerscala.property.Property
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ScopedExample extends Example {
  val loaded = Property[Int](default = Some(0))

  contents += new tag.Div(id = "replaceable", content = "Original Content")

  val replaceable = ScopedReplacement(Scope.Page, getById[tag.Div]("replaceable")) {
    case t => t.contents += s" ${loaded()}"
  }

  contents += new tag.Div(content = s"First Load: ${loaded()}")

  contents += Scoped(Scope.Page) {
    loaded := loaded() + 1
    new tag.Div(content = s"Page Loaded ${loaded()} times!")
  }
  contents += Scoped(Scope.Session) {
    new tag.Div(content = s"Session Updated at ${loaded()}")
  }
  contents += Scoped(Scope.Application) {
    new tag.Div(content = s"Application Updated at ${loaded()}")
  }
}