package org.hyperscala.css.attributes

import org.hyperscala.{ToScala, AttributeValue}
import org.hyperscala.persistence.CaseClassPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class ZIndex(n: Int = -1, name: Option[String] = None) extends AttributeValue with ToScala {
  def value = name.getOrElse(n.toString)

  import scala.reflect.runtime.universe._
  def toScala: Tree =
    this match {
      case ZIndex.Auto => q"ZIndex.Auto"
      case ZIndex.Inherit => q"ZIndex.Inherit"
      case _ => q"ZIndex.Numeric($n)"
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