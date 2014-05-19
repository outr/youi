package org.hyperscala.web

import org.hyperscala.html.HTMLTag
import org.powerscala.MappedStorage
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.powerscala.event.Listenable
import org.powerscala.property.Property
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait WrappedComponent[Tag <: HTMLTag] {
  private val storage = new MappedStorage[String, Any] {}

  @volatile private var _webpage: Webpage[Session] = _
  protected def webpage = _webpage
  def initialized = webpage != null

  protected def wrapped: Tag
  protected def autoInit: Boolean
  protected def initializeComponent(values: Map[String, Any]): Unit
  protected def modify(key: String, value: Any): Unit

  if (autoInit) init()

  def init() = {
    wrapped.connected[Webpage[Session]] {
      case w => synchronized {
        if (_webpage == null) {
          _webpage = w
          initializeComponent(storage.map)
        }
      }
    }
  }

  def option(key: String, value: Any) = {
    if (initialized) {
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
      case evt => if (!p.isChanging) {
        option(name, p.js())
      }
    }
    if (includeDefault) {
      option(name, p.js())
    }
    p
  }
}

class JavaScriptProperty[T](toJS: T => JavaScriptContent, default: Option[T])
                       (implicit parent: Listenable, manifest: Manifest[T]) extends Property[T](default = default)(parent, manifest) {
  private var _changing = false

  def isChanging = _changing

  def applyChange(value: T) = synchronized {
    _changing = true
    try {
      apply(value)
    } finally {
      _changing = false
    }
  }

  val js = () => toJS(value)
}