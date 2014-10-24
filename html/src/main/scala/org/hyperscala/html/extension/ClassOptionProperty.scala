package org.hyperscala.html.extension

import org.hyperscala.html._
import org.powerscala.property.Property

/**
 * ClassOptionProperty represents an optional ClassValue that is added or removed on the HTMLTag based on its current
 * status in this property.
 *
 * @see ClassValue
 *
 * @author Matt Hicks <matt@outr.com>
 */
class ClassOptionProperty[T <: ClassName](t: HTMLTag, default: Option[T])(implicit manifest: Manifest[T]) extends Property[Option[T]](default = Some(default))(t, implicitly[Manifest[Option[T]]]) {
  change.on {
    case evt => {
      evt.oldValue match {
        case Some(oldValue) => oldValue.className match {
          case Some(className) => t.clazz -= className
          case None => // Had no className
        }
        case None => // None
      }
      evt.newValue match {
        case Some(newValue) => newValue.className match {
          case Some(className) => t.clazz += className
          case None => // Has no className
        }
        case None => // None
      }
    }
  }
}