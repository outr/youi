package org.hyperscala.css.attributes

import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class BackgroundPosition(offsetX: Length = null,
                              horizontal: Horizontal = null,
                              offsetY: Length = null,
                              vertical: Vertical = null) {
  override def toString = {
    val b = new StringBuilder
    if (horizontal != null) {
      b.append(horizontal.name.toLowerCase)
      b.append(" ")
    }
    if (offsetX != null) {
      b.append(offsetX.value)
      b.append(" ")
    }
    if (vertical != null) {
      b.append(vertical.name.toLowerCase)
      b.append(" ")
    }
    if (offsetY != null) {
      b.append(offsetY.value)
    }
    b.toString().trim
  }
}

object BackgroundPosition extends ValuePersistence[BackgroundPosition] {
  def fromString(s: String, name: String, clazz: Class[_]) = {
    if (s != null) {
      var position = BackgroundPosition()
      s.split(" ").foreach {
        case Horizontal(h) => position = position.copy(horizontal = h)
        case Vertical(v) => position = position.copy(vertical = v)
        case Length(l) => if (position.offsetX != null) {
          position = position.copy(offsetY = l)
        } else {
          position = position.copy(offsetX = l)
        }
      }
      position
    } else {
      null
    }
  }

  def toString(t: BackgroundPosition, name: String, clazz: Class[_]) = if (t != null) {
    t.toString
  } else {
    null
  }
}