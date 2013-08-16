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
  protected def tag: HTMLTag
  protected def functionName: String
  private var values = Map.empty[String, () => Any]

  tag.onBeforeRender {
    jQuery.call(tag, functionName, values.map(t => t._1 -> t._2()))
  }

  def property[T](name: String, default: T)(implicit manifest: Manifest[T]) = synchronized {
    val p = Property[T](default = Option(default))
    p.change.on {
      case evt => if (tag.rendered) {
        propertyChanged(name, evt.newValue, p)
      }
    }
    values += name -> p
    p
  }

  def propertyChanged[T](name: String, value: T, property: Property[T]) = {
    jQuery.option(tag, functionName, name, value)
  }
}
