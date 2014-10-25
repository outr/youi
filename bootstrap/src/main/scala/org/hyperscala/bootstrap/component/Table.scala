package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.ClassBooleanProperty
import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Table extends tag.Table {
  clazz += "table"

  lazy val striped = new ClassBooleanProperty(this, enabled = Some("table-striped"))
  lazy val bordered = new ClassBooleanProperty(this, enabled = Some("table-bordered"))
  lazy val hover = new ClassBooleanProperty(this, enabled = Some("table-hover"))
  lazy val condensed = new ClassBooleanProperty(this, enabled = Some("table-condensed"))

  val head = new tag.THead
  val body = new tag.TBody

  contents.addAll(head, body)
}