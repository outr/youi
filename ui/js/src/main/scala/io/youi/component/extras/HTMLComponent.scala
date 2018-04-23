package io.youi.component.extras

import io.youi.component.Component
import io.youi.{dom, ui}
import io.youi.dom._
import org.scalajs.dom.{Element, _}
import reactify.Var

trait HTMLComponent[E <: html.Element] extends Component {
  protected def element: E
  protected val e: HTMLExtras[E] = new HTMLExtras[E](element)

  override lazy val position: HTMLComponentPosition = new HTMLComponentPosition(this)

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
}

object HTMLComponent {
  def create[T <: Element](tagName: String): T = {
    val e = dom.create[T](tagName)
    // TODO: init
    e
  }

  def element(component: Component): html.Element = component.asInstanceOf[HTMLComponent[html.Element]].element
}

sealed trait Position

object Position {
  case object Static extends Position
  case object Relative extends Position
  case object Absolute extends Position
  case object Fixed extends Position
  case object Sticky extends Position

  def apply(value: String): Position = value.toLowerCase.trim match {
    case "relative" => Relative
    case "absolute" => Absolute
    case "fixed" => Fixed
    case "sticky" => Sticky
    case _ => Static
  }
}

class HTMLComponentPosition(component: Component) extends ComponentPosition(component) {
  private def e: html.Element = HTMLComponent.element(component)

  lazy val `type`: Var[Position] = {
    component.connect[Position](
      v = Var(Position.Static),
      get = Position(e.style.position),
      set = (p: Position) => e.style.position = p.toString.toLowerCase,
      default = Position.Static
    )
  }

  component.connect(x, e.offsetLeft, (v: Double) => e.style.left = s"${v}px", 0.0)
  component.connect(y, e.offsetTop, (v: Double) => e.style.top = s"${v}px", 0.0)
}