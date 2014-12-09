package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ZIndex private(val n: Int = -1) extends EnumEntryAttributeValue {
  def value = if (n != -1) n.toString else name
}

object ZIndex extends Enumerated[ZIndex] with EnumEntryPersistence[ZIndex] {
  private val Regex = """(\d+)""".r

  val Auto = new ZIndex()
  val Inherit = new ZIndex()
  def Numeric(n: Int) = new ZIndex(n)

  override def apply(name: String) = get(name).getOrElse(Auto)

  override def get(name: String, caseSensitive: Boolean) = name.toLowerCase match {
    case null | "" => None
    case "auto" => Some(Auto)
    case "inherit" => Some(Inherit)
    case Regex(n) => Some(Numeric(n.toInt))
    case _ => None
  }
}