package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.extension.ClassBooleanProperty

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ListGroup extends tag.Div(clazz = List("list-group"))

trait ListGroupItem extends BodyChild {
  clazz += "list-group-item"

  val active   = new ClassBooleanProperty(this, enabled = Some("active"))
  val disabled = new ClassBooleanProperty(this, enabled = Some("disabled"))
}

class ListGroupItemHeading(text: String = "") extends tag.H4 {
  clazz += "list-group-item-heading"
  contents += text
}

class ListGroupItemText(text: String = "") extends tag.P {
  clazz += "list-group-item-text"
  contents += text
}
