package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.ClassBooleanProperty

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ListItem extends tag.Li {
  val active = new ClassBooleanProperty(this, enabled = Some("active"))

  def this(active: Boolean) = {
    this()
    this.active := active
  }
}