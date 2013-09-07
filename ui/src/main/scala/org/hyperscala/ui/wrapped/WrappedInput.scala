package org.hyperscala.ui.wrapped

import org.hyperscala.html._
import org.powerscala.property.Property
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class WrappedInput[T] private(input: tag.Input,
                              to: String => T,
                              from: T => String,
                              parent: Listenable,
                              manifest: Manifest[T]) {
  val property = Property[T]()(parent, manifest)

  property := to(input.value())

  property.bindTo(input.value)(to)
  input.value.bindTo(property)(from)
}

object WrappedInput {
  private def String2Int(default: Int) = (s: String) => try {
    s.toInt
  } catch {
    case t: Throwable => default
  }

  private val Int2String = (i: Int) => if (i == -1) null else i.toString

  def apply[T](input: tag.Input)
              (to: String => T)
              (from: T => String)
              (implicit parent: Listenable = null, manifest: Manifest[T]) = {
    new WrappedInput[T](input, to, from, parent, manifest)
  }

  def int(input: tag.Input, default: Int = -1) = {
    apply[Int](input)(String2Int(default))(Int2String)(null, implicitly[Manifest[Int]])
  }
}