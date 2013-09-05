package org.hyperscala.web

import org.hyperscala.html.HTMLTag
import org.powerscala.Storage
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.powerscala.event.Listenable
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait WrappedComponent[Tag <: HTMLTag] {
  private val storage = new Storage[Any] {}

  protected def wrapped: Tag
  protected def initializeComponent(values: Map[String, Any]): Unit
  protected def modify(key: String, value: Any): Unit

  wrapped.onBeforeRender {
    initializeComponent(storage.map)
  }

  def option(key: String, value: Any) = {
    if (wrapped.rendered) {
      modify(key, value)
    } else {
      storage(key) = value
    }
  }

  protected def property[T](name: String,
                            default: T,
                            includeDefault: Boolean = false,
                            toJS: T => JavaScriptContent = (t: T) => JavaScriptString(JavaScriptContent.toJS(t)))
                           (implicit manifest: Manifest[T]) = synchronized {
    val p = new JavaScriptProperty[T](toJS, default = Option(default))(wrapped, manifest)
    p.change.on {
      case evt => option(name, p.js())
    }
    if (includeDefault) {
      option(name, p.js())
    }
    p
  }
}

class JavaScriptProperty[T](toJS: T => JavaScriptContent, default: Option[T])
                       (implicit parent: Listenable, manifest: Manifest[T]) extends Property[T](default = default)(parent, manifest) {
  val js = () => toJS(value)
}