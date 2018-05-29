package io.youi.component.extras

import io.youi.component.Component
import io.youi.{dom, ui}
import io.youi.dom._
import io.youi.event.{EventSupport, HTMLEvents}
import io.youi.theme.{HTMLComponentTheme, Theme}
import io.youi.util.Measurer
import org.scalajs.dom.{Element, _}

trait HTMLComponent[E <: html.Element] extends Component with HTMLComponentTheme {
  protected def element: E
  protected val e: HTMLExtras[E] = new HTMLExtras[E](element)

  override lazy val position: HTMLComponentPosition = new HTMLComponentPosition(this)
  override lazy val size: HTMLComponentSize = new HTMLComponentSize(this)

  override val event: EventSupport = new HTMLEvents(this, element)

  override protected def init(): Unit = {
    super.init()

    element.setAttribute("data-youi-id", id())

    if (this != ui) {
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
        case None => {
          element.remove()
        }
      }
    }
  }

  override protected def measuredWidth: Double = Measurer.measure(element).width
  override protected def measuredHeight: Double = Measurer.measure(element).height
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