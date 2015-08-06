package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.extension.ClassBooleanProperty

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Description extends tag.Dl {
  val horizontal = new ClassBooleanProperty(this, enabled = Some("dl-horizontal"))

  def add(term: BodyChild, definition: BodyChild) {
    contents += new tag.Dt(content = term)
    contents += new tag.Dd(content = definition)
  }
}
