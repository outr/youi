package org.hyperscala.css.attributes

import org.hyperscala.AttributeValue
import org.hyperscala.persistence.CaseClassPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class ZIndex(n: Int = -1, name: Option[String] = None) extends AttributeValue {
  def value = name match {
    case Some(nme) => nme
    case None => n.toString
  }
}

object ZIndex extends CaseClassPersistence[ZIndex] {
  private val Regex = """(\d+)""".r

  val Auto = new ZIndex()
  val Inherit = new ZIndex()
  def Numeric(n: Int) = new ZIndex(n)

  def apply(name: String) = get(name).getOrElse(Auto)

  def get(name: String) = name.toLowerCase match {
    case null | "" => None
    case "auto" => Some(Auto)
    case "inherit" => Some(Inherit)
    case Regex(n) => Some(Numeric(n.toInt))
    case _ => None
  }
}