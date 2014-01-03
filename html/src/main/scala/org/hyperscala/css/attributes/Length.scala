package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Length(val value: String) extends EnumEntry with AttributeValue {
  lazy val pixels = if (value.endsWith("px")) {
    value.substring(0, value.length - 2).toInt
  } else {
    0
  }

  lazy val percent = if (value.endsWith("%")) {
    value.substring(0, value.length - 1).toInt
  } else {
    0
  }

  override def toString() = if (name != null) {
    super.toString()
  } else {
    value
  }
}

object Length extends Enumerated[Length] with EnumEntryPersistence[Length] {
  val NumberRegex = """(\d+)""".r

  val Auto = new Length("auto")
  val Inherit = new Length("inherit")
  def Pixels(v: Int) = Length("%spx".format(v))
  def Centimeters(v: Int) = Length("%scm".format(v))
  def Percent(v: Int) = Length(v + "%")

  override def apply(name: String): Length = name match {
    case null => null
    case NumberRegex(n) => Pixels(n.toInt)
    case _ => super.apply(name) match {
      case null => new Length(name)
      case v => v
    }
  }
}