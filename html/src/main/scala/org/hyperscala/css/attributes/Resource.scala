package org.hyperscala.css.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Resource(val value: String) extends EnumEntry[Resource] with AttributeValue

object Resource extends Enumerated[Resource] with EnumEntryPersistence[Resource] {
  val None = new Resource("none")
  val Inherit = new Resource("inherit")

  override def apply(name: String) = super.apply(name) match {
    case null => new Resource("url('%s')".format(name))
    case v => v
  }
}