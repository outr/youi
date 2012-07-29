package org.hyperscala.html.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Target(val value: String) extends EnumEntry[Target] with AttributeValue

object Target extends Enumerated[Target] with EnumEntryPersistence[Target] {
  val Blank = new Target("_blank")
  val Self = new Target("_self")
  val Parent = new Target("_parent")
  val Top = new Target("_top")

  override def apply(name: String) = super.apply(name) match {
    case null => new Target("%s".format(name))
    case v => v
  }
}