package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.extension.ClassBooleanProperty

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Badge extends tag.Span {
  def this(content: BodyChild, pullRight: Boolean = false) {
    this()

    contents += content
    if (pullRight) this.pullRight := true
  }

  clazz += "badge"

  lazy val pullRight = new ClassBooleanProperty(this, enabled = Some("pull-right"))
}
