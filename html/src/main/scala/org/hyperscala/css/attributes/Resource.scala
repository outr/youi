package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Resource(val value: String) extends EnumEntry with AttributeValue

object Resource extends Enumerated[Resource] with EnumEntryPersistence[Resource] {
  val None = new Resource("none")
  val Inherit = new Resource("inherit")

  override def apply(name: String) = super.apply(name) match {
    case null => new Resource("url('%s')".format(name))
    case v => v
  }
}