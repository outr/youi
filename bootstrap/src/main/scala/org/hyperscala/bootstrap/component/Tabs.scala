package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.html.extension.ClassBooleanProperty
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Tabs extends tag.Ul(clazz = List("nav")) {
  val tabs = new ClassBooleanProperty(this, default = true, enabled = Some("nav-tabs"))
  val pills = new ClassBooleanProperty(this, enabled = Some("nav-pills"))
  val stacked = new ClassBooleanProperty(this, enabled = Some("nav-stacked"))
  val justified = new ClassBooleanProperty(this, enabled = Some("nav-justified"))
}

class TabEntry extends tag.Li {
  val active = new ClassBooleanProperty(this, enabled = Some("active"))
  val disabled = new ClassBooleanProperty(this, enabled = Some("disabled"))
}