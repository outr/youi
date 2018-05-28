package io.youi.theme

import io.youi.component.extras.HTMLComponent
import reactify._
import org.scalajs.dom._
import io.youi.dom._

trait StyleConnect[T] {
  def init(theme: Theme, v: Var[T], name: String): Unit
}

object StyleConnect {
  private def withElement(theme: Theme)(f: html.Element => Unit): Unit = theme match {
    case c: HTMLComponent[_] => {
      val e = HTMLComponent.element(c)
      f(e)
    }
    case _ => // Not an HTMLComponent
  }

  def field[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: Var[T], name: String): Unit = withElement(theme) { e =>
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
  })

  def style[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = style[T]((t: T) => t)(stringify)

  def style[T](modifier: T => T)
              (implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: Var[T], name: String): Unit = withElement(theme) { e =>
      val current = stringify.fromString(window.getComputedStyle(e).getPropertyValue(name))
      val default = v()
      v.attach { value =>
        stringify.toString(modifier(value)) match {
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
  })

  def content[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: Var[T], name: String): Unit = withElement(theme) { e =>
      val current = stringify.fromString(window.getComputedStyle(e).getPropertyValue(name))
      val default = v()
      v.attach { value =>
        stringify.toString(value) match {
          case Some(s) => e.innerHTML = s
          case None => e.innerHTML = ""
        }
      }
      current.foreach { value =>
        if (value != default) {
          v := value
        }
      }
    }
  })

  def classify[T](implicit stringify: Stringify[T]): Option[StyleConnect[T]] = Some(new StyleConnect[T] {
    override def init(theme: Theme, v: Var[T], name: String): Unit = withElement(theme) { e =>
      scribe.info(s"Initializing $name...")
      val initialValue = e.classList.toList.flatMap(stringify.fromString).headOption.getOrElse(v())
      v := initialValue
      stringify.toString(initialValue).foreach(e.classList.add)
      v.changes(new ChangeObserver[T] {
        override def change(oldValue: T, newValue: T): Unit = {
          scribe.info(s"Classify changed $oldValue / $newValue")
          stringify.toString(oldValue).foreach(e.classList.remove)
          stringify.toString(newValue).foreach(e.classList.add)
        }
      })
      v.attach { value =>
        scribe.info(s"Classify fired: $value")
      }
    }
  })

  def flag(on: Option[String] = None, off: Option[String] = None): Option[StyleConnect[Boolean]] = Some(new StyleConnect[Boolean] {
    override def init(theme: Theme, v: Var[Boolean], name: String): Unit = withElement(theme) { e =>
      val classes = e.classList.toSet
      val isOn = on.exists(classes.contains)
      val isOff = off.exists(classes.contains)
      if (isOn) {
        v := true
      } else if (isOff) {
        v := false
      }
      v.attachAndFire {
        case true => {
          off.foreach(e.classList.remove)
          on.foreach(e.classList.add)
        }
        case false => {
          on.foreach(e.classList.remove)
          off.foreach(e.classList.add)
        }
      }
    }
  })
}