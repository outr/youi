package org.hyperscala.ui.convert

import org.powerscala.reflect._
import org.powerscala.enum.EnumEntry

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Converter[V] {
  def value2String(value: V): String

  def string2Value(s: String, clazz: EnhancedClass): Option[V]
}

object Converter {
  private var map = Map.empty[Class[_], Converter[_]]

  register(classOf[Int], IntConverter)
  register(classOf[Long], LongConverter)
  register(classOf[Double], DoubleConverter)
  register(classOf[String], StringConverter)
  register(classOf[EnumEntry], EnumConverter)

  def register[V](clazz: Class[V], converter: Converter[V]) = {
    map += clazz -> converter
  }

  final def apply[V](clazz: Class[V]): Option[Converter[V]] = {
    val option = map.get(clazz).asInstanceOf[Option[Converter[V]]]
    if (option.isDefined) {
      option
    } else {
      clazz.parents.toStream.map(c => apply[V](c.asInstanceOf[Class[V]])).flatten.headOption
    }
  }
}