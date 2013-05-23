package org.hyperscala.svg.attributes

import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
case class ViewBox(x: Double, y: Double, width: Double, height: Double)

object ViewBox extends ValuePersistence[ViewBox] {
  def fromString(s: String, name: String, clazz: Class[_]) = {
    val args = s.split(" ").map(v => v.toDouble)
    ViewBox(args(0), args(1), args(2), args(3))
  }

  def toString(t: ViewBox, name: String, clazz: Class[_]) = "%s %s %s %s".format(t.x, t.y, t.width, t.height)
}