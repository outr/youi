package io.youi.component.extras

import io.youi.component.Component
import io.youi.{dom, ui}
import io.youi.dom._
import org.scalajs.dom.{Element, _}

trait HTMLComponent[E <: html.Element] extends Component {
  protected def element: E
  protected val e: HTMLExtras[E] = new HTMLExtras[E](element)

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

  def updateFromHTML(): Unit = {
    props.foreach(_.update())
  }
}

object HTMLComponent {
  def create[T <: Element](tagName: String): T = {
    val e = dom.create[T](tagName)
    // TODO: init
    e
  }

  def element(component: Component): html.Element = component.asInstanceOf[HTMLComponent[html.Element]].element
}