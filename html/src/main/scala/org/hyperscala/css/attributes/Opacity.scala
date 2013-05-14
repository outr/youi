package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class Opacity(val value: String) extends EnumEntry with AttributeValue

object Opacity extends Enumerated[Opacity] with EnumEntryPersistence[Opacity] {
  val Inherit = new Opacity("inherit")
  def apply(v: Double): Opacity = Opacity(v.toString)

  override def apply(name: String) = super.apply(name) match {
    case null => new Opacity(name)
    case v => v
  }
}