package org.hyperscala.html.extension

import org.hyperscala.html.HTMLTag
import org.powerscala.event.Listenable
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
abstract class AbstractClassProperty[T](t: HTMLTag, default: T)(implicit parent: Listenable, manifest: Manifest[T]) extends Property[T](default = Some(default)) {
  toClass(default) match {
    case Some(c) => t.clazz += c
    case None => // Nothing to set
  }

  change.on {
    case evt => {
      toClass(evt.oldValue) match {
        case Some(c) => t.clazz -= c
        case None => // Nothing to remove
      }
      toClass(evt.newValue) match {
        case Some(c) => t.clazz += c
        case None => // Nothing to add
      }
    }
  }

  def toClass(c: T): Option[String]
}