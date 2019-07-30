package io.youi.component.extras

import io.youi.component.{AbstractContainer, Component}
import io.youi.{AnimationFrame, ResizeObserver, ResizeObserverEntry, dom, ui}
import io.youi.dom._
import io.youi.event.{EventSupport, HTMLEvents}
import io.youi.style.{Display, Visibility}
import io.youi.theme.{HTMLComponentTheme, Theme}
import org.scalajs.dom.{Element, _}
import reactify.{Val, Var}

import scala.scalajs.js

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

  private var scrolling: Boolean = false

  override protected def init(): Unit = {
    super.init()

    id.attachAndFire { _ =>
      element.setAttribute("data-youi-id", id())
      element.setAttribute("id", id())
    }

    visible.attach { b =>
      if (b) invalidateTransform()
    }

    val e = if (this == ui) window else element
    e.addEventListener("scroll", (_: Event) => {
      invalidateTransform()
    })

    if (ui.supportsResizeObserver) {
      val observer = new ResizeObserver((entries: js.Array[ResizeObserverEntry]) => {
        invalidateTransform()
      })
      observer.observe(element)
    }

    position.scroll.x.attach { v =>
      if (!scrolling) {
        element.scrollLeft = v
      }
    }
    position.scroll.y.attach { v =>
      if (!scrolling) {
        element.scrollTop = v
      }
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

  override def updateTransform(): Unit = {
    super.updateTransform()

    size.view.width.asInstanceOf[Var[Double]] := element.clientWidth
    size.view.height.asInstanceOf[Var[Double]] := element.clientHeight
    val rect = element.getBoundingClientRect()
    scrolling = true
    try {
      position.scroll.x := rect.left
      position.scroll.y := rect.top
      size.scroll.width.asInstanceOf[Var[Double]] := rect.width
      size.scroll.height.asInstanceOf[Var[Double]] := rect.height
    } finally {
      scrolling = false
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

  def find(component: Component, element: EventTarget): Option[Component] = component match {
    case c: HTMLComponent[_] if c.element == element => Some(c)
    case c: AbstractContainer[_] => AbstractContainer.children(c).flatMap(HTMLComponent.find(_, element)).headOption
    case _ => None
  }
}