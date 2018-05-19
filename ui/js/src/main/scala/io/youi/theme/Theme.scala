package io.youi.theme

import io.youi.MapStore
import io.youi.component.extras.HTMLComponent
import reactify._
import org.scalajs.dom._

trait Theme {
  lazy val parentTheme: Var[Option[Theme]] = Var(None)

  private val store = new MapStore

  private def get[T](name: String): Option[T] = store.get[T](name).orElse(parentTheme().flatMap(_.get[T](name)))

  protected def style[T](name: String, default: => T, connect: Option[StyleConnect[T]]): Var[T] = {
    val v = Var[T](parentTheme().flatMap(_.get[T](name)).getOrElse(default))
    connect.foreach(_.init(this, v, name))
    v
  }
}

class ButtonTheme extends Theme {
  lazy val value: Var[String] = style[String]("value", "", StyleConnect.field[String])
}

trait StyleConnect[T] {
  def init(theme: Theme, v: Var[T], name: String): Unit
}

object StyleConnect {
  def field[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: Var[T], name: String): Unit = theme match {
      case c: HTMLComponent[_ <: html.Element] => {
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
}

trait Stringify[T] {
  def fromString(value: String): Option[T]
  def toString(value: T): Option[String]
}