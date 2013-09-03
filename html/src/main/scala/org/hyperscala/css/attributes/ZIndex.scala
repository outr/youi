package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ZIndex private(val n: Int = -1) extends EnumEntry with AttributeValue {
  def value = if (n != -1) n.toString else name
}

object ZIndex extends Enumerated[ZIndex] with EnumEntryPersistence[ZIndex] {
  private val Regex = """(\d+)""".r

  val Auto = new ZIndex()
  val Inherit = new ZIndex()
  def Numeric(n: Int) = new ZIndex(n)

  override def apply(name: String) = name match {
    case Regex(n) => Numeric(n.toInt)
    case _ => super.apply(name)
  }
}