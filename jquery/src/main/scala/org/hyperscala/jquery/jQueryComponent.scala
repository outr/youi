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
        jQuery.call(tag, functionName, Map(name -> evt.newValue))
      }
    }
    values += name -> p
    p
  }
}
