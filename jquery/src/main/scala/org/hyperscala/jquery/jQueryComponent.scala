package org.hyperscala.jquery

import org.hyperscala.html.HTMLTag
import org.powerscala.property.Property

/**
 * jQueryComponent trait works to provide easier access to making calls to jQuery for extensions like autocomplete and
 * other jQuery plugins.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait jQueryComponent {
  protected def html: HTMLTag
  protected def functionName: String
  private var values = Map.empty[String, () => Any]

  html.onBeforeRender {
    jQuery.call(html, functionName, values.map(t => t._1 -> t._2()))
  }

  protected def property[T](name: String, default: T, includeDefault: Boolean = false)(implicit manifest: Manifest[T]) = synchronized {
    val p = Property[T](default = Option(default))
    p.change.on {
      case evt => propertyChanged(name, evt.newValue, p)
    }
    if (includeDefault) {
      values += name -> p
    }
    p
  }

  protected def propertyChanged[T](name: String, value: T, property: Property[T]) = {
    if (html.rendered) {
      jQuery.option(html, functionName, name, value)
    } else {
      values += name -> property
    }
  }

  def option(key: String, value: Any) = {
    if (html.rendered) {
      jQuery.option(html, functionName, key, value)
    } else {
      values += key -> (() => value)
    }
  }

  def call(function: String) = {
    jQuery.call(html, s"$functionName('$function')")
  }
}
