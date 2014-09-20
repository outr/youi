package org.hyperscala.css.attributes

import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Rect(val top: Length = null, val right: Length = null, val bottom: Length = null, val left: Length = null) {
  override def toString = if (top == null && right == null && bottom == null && left == null) {
    "auto"
  } else {
    s"rect(${lengthValue(top)}, ${lengthValue(right)}, ${lengthValue(bottom)}, ${lengthValue(left)})"
  }

  private def lengthValue(l: Length) = if (l != null && l.value.nonEmpty) l.value else "auto"
}

object Rect extends ValuePersistence[Rect] {
  val RegexStandard = """rect\((.+), (.+), (.+), (.+)\)""".r
  val RegexNoCommas = """rect\((.+) (.+) (.+) (.+)\)""".r

  def apply(s: String) = s match {
    case RegexStandard(t, r, b, l) => new Rect(Length(t), Length(r), Length(b), Length(l))
    case RegexNoCommas(t, r, b, l) => new Rect(Length(t), Length(r), Length(b), Length(l))
    case "auto" => new Rect()
    case "" => new Rect()
    case _ => throw new RuntimeException(s"Unsupported rect format: [$s]")
  }

  def apply(top: Length = null, right: Length = null, bottom: Length = null, left: Length = null) = {
    new Rect(top, right, bottom, left)
  }

  def toString(t: Rect) = if (t == null) null else t.toString

  def fromString(s: String, name: String, clazz: Class[_]) = apply(s)

  def toString(t: Rect, name: String, clazz: Class[_]): String = toString(t)
}