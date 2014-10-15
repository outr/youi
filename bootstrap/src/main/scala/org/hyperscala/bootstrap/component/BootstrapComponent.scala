package org.hyperscala.bootstrap.component

import org.powerscala.property.Property
import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait BootstrapComponent extends HTMLTag {
  protected def classProp[E <: ClassNameEnumEntry](default: E)(implicit manifest: Manifest[E]) = {
    val p = Property[E](default = Some(default))
    p.change.on {
      case evt => {
        if (evt.oldValue != null) {
          clazz -= evt.oldValue.className
        }
        if (evt.newValue != null && evt.newValue.className != "") {
          clazz += evt.newValue.className
        }
      }
    }
    if (default != null && default.className != "") {
      clazz += default.className
    }
    p
  }

  protected def boolProp(className: String, default: Boolean = false) = {
    val p = Property[Boolean](default = Some(default))
    p.change.on {
      case evt => if (evt.newValue) {
        clazz += className
      } else {
        clazz -= className
      }
    }
    if (default) clazz += className
    p
  }
}
