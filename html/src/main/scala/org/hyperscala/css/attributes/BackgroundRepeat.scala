package org.hyperscala.css.attributes

import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class BackgroundRepeat(horizontal: Repeat, vertical: Repeat) {
  override def toString = if (horizontal == vertical) {
    toString(horizontal)
  } else {
    s"${toString(horizontal)} ${toString(vertical)}"
  }

  private def toString(repeat: Repeat) = if (repeat != null) {
    repeat.value
  } else {
    "inherit"
  }
}

object BackgroundRepeat extends ValuePersistence[BackgroundRepeat] {
  def fromString(s: String, name: String, clazz: Class[_]) = {
    if (s != null) {
      val repeats = s.split(" ").map(s => Repeat(s))
      if (repeats.length == 1) {
        BackgroundRepeat(repeats(0), repeats(0))
      } else {
        BackgroundRepeat(repeats(0), repeats(1))
      }
    } else {
      null
    }
  }

  def toString(t: BackgroundRepeat, name: String, clazz: Class[_]) = if (t != null) {
    t.toString
  } else {
    null
  }
}