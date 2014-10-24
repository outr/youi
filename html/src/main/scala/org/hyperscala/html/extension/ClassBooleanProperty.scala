package org.hyperscala.html.extension

import org.hyperscala.html._
import org.powerscala.property.Property

/**
 * ClassBooleanProperty allows a Boolean property to be represented for an HTMLTag that manages a CSS class for the
 * enabled and/or disabled states.
 *
 * @see ClassValue
 *
 * @author Matt Hicks <matt@outr.com>
 */
class ClassBooleanProperty(t: HTMLTag, default: Boolean = false, enabled: Option[String] = None, disabled: Option[String] = None) extends Property[Boolean](default = Some(default)) {
  change.on {
    case evt => {
      val oldValue = if (evt.oldValue) enabled else disabled
      oldValue match {
        case Some(className) => t.clazz -= className
        case None => // Had no className
      }
      val newValue = if (evt.newValue) enabled else disabled
      newValue match {
        case Some(className) => t.clazz += className
        case None => // Has no className
      }
    }
  }
}