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

  override def get(name: String, caseSensitive: Boolean = false) = super.get(name, caseSensitive) match {
    case Some(v) => Some(v)
    case scala.None => if (name.toLowerCase.startsWith("url(")) {
      Some(new Resource(name))
    } else {
      Some(new Resource("url('%s')".format(name)))
    }
  }
}