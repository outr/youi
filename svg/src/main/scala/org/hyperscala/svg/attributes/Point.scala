package org.hyperscala.svg.attributes

import org.hyperscala.persistence.ValuePersistence

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
case class Point(x: Double, y: Double)

object Point extends ValuePersistence[List[Point]] {
  def apply(values: Double*): List[Point] = values.grouped(2).map(s => Point(s(0), s(1))).toList

  def fromString(s: String, name: String, clazz: Class[_]) = throw new RuntimeException("Unsupported")

  def toString(t: List[Point], name: String, clazz: Class[_]) = t.map(p => "%s,%s".format(p.x, p.y)).mkString(" ")
}