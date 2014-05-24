package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Resource(val value: String) extends EnumEntryAttributeValue

object Resource extends Enumerated[Resource] with EnumEntryPersistence[Resource] {
  val None = new Resource("none")
  val Inherit = new Resource("inherit")

  override def apply(name: String) = super.apply(name) match {
    case null => if (name.startsWith("url(")) {
      new Resource(name)
    } else {
      new Resource("url('%s')".format(name))
    }
    case v => v
  }
}