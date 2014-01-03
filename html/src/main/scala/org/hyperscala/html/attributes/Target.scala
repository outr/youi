package org.hyperscala.html.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Target(val value: String) extends EnumEntry with AttributeValue

object Target extends Enumerated[Target] with EnumEntryPersistence[Target] {
  val Blank = new Target("_blank")
  val Self = new Target("_self")
  val Parent = new Target("_parent")
  val Top = new Target("_top")

  override def apply(name: String) = super.apply(name) match {
    case null => new Target(name)
    case v => v
  }
}