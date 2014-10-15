package org.hyperscala.bootstrap.component

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ListItem extends tag.Li with BootstrapComponent {
  val active = boolProp("active", default = false)

  def this(active: Boolean) = {
    this()
    this.active := active
  }
}