package org.hyperscala.css.attributes

import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class BackgroundSize(horizontal: Length = null, vertical: Length = null) {
  override def toString = if (horizontal == vertical) {
    toString(horizontal)
  } else {
    s"${toString(horizontal)} ${toString(vertical)}"
  }

  private def toString(l: Length) = if (l != null) {
    l.value
  } else {
    "auto"
  }
}

object BackgroundSize extends ValuePersistence[BackgroundSize] {
  def fromString(s: String, name: String, clazz: Class[_]) = if (s == null) {
    null
  } else if (s.trim.indexOf(' ') > -1) {
    val parsed = s.trim.split(" ")
    BackgroundSize(Length(parsed(0)), Length(parsed(1)))
  } else {
    val l = Length(s)
    BackgroundSize(l, l)
  }

  def toString(t: BackgroundSize, name: String, clazz: Class[_]) = if (t != null) {
    t.toString
  } else {
    null
  }
}