package org.hyperscala.ui.convert

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Converter[V] {
  def value2String(value: V): String

  def string2Value(s: String): Option[V]
}

object Converter {
  private var map = Map.empty[Class[_], Converter[_]]

  register(classOf[Int], IntConverter)
  register(classOf[Double], DoubleConverter)
  register(classOf[String], StringConverter)

  def register[V](clazz: Class[V], converter: Converter[V]) = {
    map += clazz -> converter
  }

  def apply[V](clazz: Class[V]) = map.get(clazz)
}