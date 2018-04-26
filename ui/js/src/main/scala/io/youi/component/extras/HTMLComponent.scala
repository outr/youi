package io.youi.component.extras

import io.youi.component.Component
import io.youi.{dom, ui}
import io.youi.dom._
import io.youi.paint.Paint
import org.scalajs.dom.{Element, _}
import reactify.Var

trait HTMLComponent[E <: html.Element] extends Component {
  protected def element: E
  protected val e: HTMLExtras[E] = new HTMLExtras[E](element)

  override lazy val position: HTMLComponentPosition = new HTMLComponentPosition(this)
  lazy val rotation: Var[Double] = connect(Var(0.0), (d: Double) => element.style.transform = s"rotate(${d * 360.0}deg)")

  override protected def init(): Unit = {
    super.init()

    element.setAttribute("data-youi-id", id())

    connect(size.width, (v: Double) => element.style.width = s"${v}px")
    connect(size.height, (v: Double) => element.style.height = s"${v}px")

    connect(visible, (b: Boolean) => element.style.visibility = if (b) "visible" else "hidden")
    connect(opacity, (d: Double) => element.style.opacity = d.toString)
    connect(background, (p: Paint) => element.style.background = p.asCSS())

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

  override protected def measuredWidth: Double = element.offsetWidth // + margin.left() + margin.right()
  override protected def measuredHeight: Double = element.offsetHeight // + margin.top() + margin.bottom()
}

object HTMLComponent {
  def create[T <: Element](tagName: String): T = {
    val e = dom.create[T](tagName)
    // TODO: init
    e
  }

  def element(component: Component): html.Element = component.asInstanceOf[HTMLComponent[html.Element]].element
}