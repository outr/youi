package org.hyperscala.css.attributes

import org.powerscala.Color
import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class TextShadow(color: Color = null, offsetX: Length = null, offsetY: Length = null, blurRadius: Length = null) {
  override def toString = {
    val b = new StringBuilder
    if (color != null) {
      b.append(color.hex.rgb)
      b.append(' ')
    }
    b.append(toString(offsetX))
    b.append(' ')
    b.append(toString(offsetY))
    b.append(' ')
    b.append(toString(blurRadius))
    b.toString()
  }

  private def toString(l: Length) = if (l != null) {
    l.value
  } else {
    "0px"
  }
}

object TextShadow extends ValuePersistence[TextShadow] {
  val Regex1 = """(.+) (.+) (.+) (.+)""".r

  def fromString(s: String, name: String, clazz: Class[_]) = if (s != null) {
    s match {
      case Regex1(color, offsetX, offsetY, blurRadius) => TextShadow(Color(color), Length(offsetX), Length(offsetY), Length(blurRadius))
    }
  } else {
    null
  }

  def toString(t: TextShadow, name: String, clazz: Class[_]) = if (t != null) {
    t.toString
  } else {
    null
  }
}