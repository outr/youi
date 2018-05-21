package io.youi.theme

import io.youi.component.extras.HTMLComponent
import reactify.Var
import org.scalajs.dom._

trait StyleConnect[T] {
  def init(theme: Theme, v: Var[T], name: String): Unit
}

object StyleConnect {
  def field[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: Var[T], name: String): Unit = theme match {
      case c: HTMLComponent[_] => {
        val e = HTMLComponent.element(c)
        val current = stringify.fromString(e.getAttribute(name))
        val default = v()
        v.attach { value =>
          stringify.toString(value) match {
            case Some(s) => e.setAttribute(name, s)
            case None => e.removeAttribute(name)
          }
        }
        current.foreach { value =>
          if (value != default) {
            v := value
          }
        }
      }
      case _ => // Not a component
    }
  })
  def style[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: Var[T], name: String): Unit = theme match {
      case c: HTMLComponent[_] => {
        val e = HTMLComponent.element(c)
        val current = stringify.fromString(window.getComputedStyle(e).getPropertyValue(name))
        val default = v()
        v.attach { value =>
          stringify.toString(value) match {
            case Some(s) => e.style.setProperty(name, s)
            case None => e.style.removeProperty(name)
          }
        }
        current.foreach { value =>
          if (value != default) {
            v := value
          }
        }
      }
      case _ => // Not a component
    }
  })
}