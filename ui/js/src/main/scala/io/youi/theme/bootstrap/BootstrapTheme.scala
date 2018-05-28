package io.youi.theme.bootstrap

import io.youi.component.extras.{Classifiable, HTMLComponent}
import io.youi.theme.HTMLComponentTheme
import reactify.{ChangeObserver, Var}
import org.scalajs.dom._
import io.youi.dom._

trait BootstrapTheme extends HTMLComponentTheme {
  private def withElement[Return](f: Option[html.Element] => Return): Return = this match {
    case c: HTMLComponent[_] => {
      val e = HTMLComponent.element(c)
      f(Some(e))
    }
    case _ => f(None)
  }

  protected def classify[T](v: Var[T], classifiable: Classifiable[T]): Var[T] = withElement { element =>
    val initialValue = element.map(_.classList.toList).getOrElse(Nil).flatMap(classifiable.fromString).headOption.getOrElse(v())
    v := initialValue
    element.foreach { e =>
      e.classList.add(classifiable.toString(initialValue))
      v.changes(new ChangeObserver[T] {
        override def change(oldValue: T, newValue: T): Unit = {
          val oldClassName = classifiable.toString(oldValue)
          e.classList.remove(oldClassName)
          val newClassName = classifiable.toString(newValue)
          e.classList.add(newClassName)
        }
      })
    }
    v
  }

  protected def classifyFlag(v: Var[Boolean], on: Option[String] = None, off: Option[String] = None): Var[Boolean] = withElement { elementOption =>
    val classes = elementOption.map(_.classList.toSet).getOrElse(Set.empty)
    val isOn = on.exists(classes.contains)
    val isOff = off.exists(classes.contains)
    if (isOn) {
      v := true
    } else if (isOff) {
      v := false
    }
    elementOption.foreach { element =>
      v.attachAndFire {
        case true => {
          off.foreach(element.classList.remove)
          on.foreach(element.classList.add)
        }
        case false => {
          on.foreach(element.classList.remove)
          off.foreach(element.classList.add)
        }
      }
    }
    v
  }
}