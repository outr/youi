package io.youi.component.extras

import io.youi.component.Component
import io.youi.{AnimationFrame, dom, ui}
import io.youi.dom._
import io.youi.event.{EventSupport, HTMLEvents}
import io.youi.style.{Display, Visibility}
import io.youi.theme.{HTMLComponentTheme, Theme}
import org.scalajs.dom.{Element, _}
import reactify.{Val, Var}

trait HTMLComponent[E <: html.Element] extends Component with HTMLComponentTheme {
  protected def element: E
  protected val e: HTMLExtras[E] = new HTMLExtras[E](element)

  def existing: Boolean

  override lazy val position: HTMLComponentPosition = new HTMLComponentPosition(this)
  override lazy val size: HTMLComponentSize = new HTMLComponentSize(this)

  override val visible: Val[Boolean] = Val(visibility() == Visibility.Visible && display() != Display.None && parent().exists(_.visible()))

  Option(element.getAttribute("id")).foreach {
    case "" => // Ignore
    case s => id := s
  }

  lazy val classList: Var[List[String]] = {
    val v = Var(element.classList.toList)
    v.attach { list =>
      val previous = element.classList.toList
      previous.diff(list).foreach { c =>
        element.classList.remove(c)
      }
      list.diff(previous).foreach { c =>
        element.classList.add(c)
      }
    }
    v
  }

  object data {
    def apply(name: String): Attribute = new Attribute("data", name)
  }
  object aria {
    def apply(name: String): Attribute = new Attribute("aria", name)
  }

  override val event: EventSupport = new HTMLEvents(this, element)

  override protected def init(): Unit = {
    super.init()

    id.attachAndFire { s =>
      element.setAttribute("data-youi-id", id())
      element.setAttribute("id", id())
    }

    if (this != ui && !existing) {
      parent.attachAndFire {
        case Some(p) => {
          sibling.previous() match {
            case Some(previous) => {
              val previousElement = HTMLComponent.element(previous)
              element.insertAfter(previousElement)
            }
            case None => {
              val parent = HTMLComponent.element(p)
              element.insertFirst(parent)
            }
          }
        }
        case None => element.remove()
      }
    }

    if (existing) {
      AnimationFrame.delta.attach(update)
    }
  }

  override protected def measuredWidth: Double = size.view.width
  override protected def measuredHeight: Double = size.view.height

  class Attribute(attribute: String, name: String) {
    lazy val key: String = s"$attribute-$name"

    def apply(): Option[String] = Option(element.getAttribute(key))
    def :=(value: String): Unit = element.setAttribute(key, value)
    def toVar: Var[Option[String]] = {
      val v = Var(apply())
      v.attach {
        case Some(s) => element.setAttribute(key, s)
        case None => element.removeAttribute(key)
      }
      v
    }
  }
}

object HTMLComponent extends HTMLComponentTheme {
  override protected def defaultParentTheme: Theme = Component

  def create[T <: Element](tagName: String): T = {
    val e = dom.create[T](tagName)
    // TODO: init
    e
  }

  def element(component: Component): html.Element = component.asInstanceOf[HTMLComponent[html.Element]].element
}