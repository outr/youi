package org.hyperscala.value

import org.powerscala.{MutableColor, ImmutableColor, Color}


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Value[T] {
  def value: T

  override final def toString = Value.convert(value)
}

object Value {
  private var map = Map.empty[Class[_], Any => String]

  register(classOf[Color], classOf[ImmutableColor], classOf[MutableColor]) {
    case color => color.asInstanceOf[Color].hex.rgb
  }

  def convert(v: Any) = {
    map.get(v.asInstanceOf[AnyRef].getClass) match {
      case None => v.toString
      case Some(converter) => converter(v)
    }
  }

  def register(clazz: Class[_]*)(f: Any => String) = synchronized {
    clazz.foreach(c => map += c -> f)
  }
}