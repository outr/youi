package org.hyperscala.html.extension

import org.hyperscala.html.HTMLTag
import org.powerscala.property.Property

/**
 * ClassProperty manages a CSS class added or removed on the HTMLTag based on its availability and status in the ClassValue.
 *
 * @see ClassValue
 *
 * @author Matt Hicks <matt@outr.com>
 */
class ClassProperty[T <: ClassName](t: HTMLTag, default: T)(implicit manifest: Manifest[T]) extends Property[T](default = Some(default))(t, manifest) {
  default.className match {
    case Some(className) => t.clazz += className
    case None => // Ignore
  }
  change.on {
    case evt => {
      evt.oldValue.className match {
        case Some(className) => t.clazz -= className
        case None => // Had no className
      }
      evt.newValue.className match {
        case Some(className) => t.clazz += className
        case None => // Has no className
      }
    }
  }
}