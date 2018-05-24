package io.youi.component.extras

import io.youi.component.Component
import io.youi.{dom, ui}
import io.youi.dom._
import io.youi.event.{EventSupport, HTMLEvents}
import io.youi.theme.HTMLComponentTheme
import io.youi.util.Measurer
import org.scalajs.dom.{Element, _}
import reactify.{ChangeObserver, Var}

trait HTMLComponent[E <: html.Element] extends Component with HTMLComponentTheme {
  protected def element: E
  protected val e: HTMLExtras[E] = new HTMLExtras[E](element)

  parentTheme := Some(HTMLComponent)

  override lazy val position: HTMLComponentPosition = new HTMLComponentPosition(this)

  override lazy val event: EventSupport = new HTMLEvents(this, element)

//  connect(size.width, if (element.offsetWidth > 0.0) Some(element.offsetWidth) else None, (v: Double) => if (v > 0.0 && v != size.measured.width()) element.style.width = s"${v}px" else element.style.removeProperty("width"))
//  connect(size.height, if (element.offsetHeight > 0.0) Some(element.offsetHeight) else None, (v: Double) => if (v > 0.0 && v != size.measured.height()) element.style.height = s"${v}px" else element.style.removeProperty("height"))

//  connect(visible, if (element.style.visibility == "hidden") Some(false) else None, (b: Boolean) => if (b) element.style.removeProperty("visibility") else element.style.visibility = "hidden")
//  connect(opacity, element.style.opacity match {
//    case null | "" => None
//    case s => {
//      val o = s.toDouble
//      if (o == 1.0) {
//        None
//      } else {
//        Some(o)
//      }
//    }
//  }, (d: Double) => if (d == 1.0) element.style.removeProperty("opacity") else element.style.opacity = d.toString)
//  connect(background, None, (p: Paint) => element.style.background = p.asCSS())

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

  protected def classify[T](v: Var[T], classifiable: Classifiable[T]): Var[T] = {
    val initialValue = element.classList.toList.flatMap(classifiable.fromString).headOption.getOrElse(v())
    v := initialValue
    element.classList.add(classifiable.toString(initialValue))
    v.changes(new ChangeObserver[T] {
      override def change(oldValue: T, newValue: T): Unit = {
        val oldClassName = classifiable.toString(oldValue)
        element.classList.remove(oldClassName)
        val newClassName = classifiable.toString(newValue)
        element.classList.add(newClassName)
      }
    })
    v
  }

  protected def classifyFlag(v: Var[Boolean], on: Option[String] = None, off: Option[String] = None): Var[Boolean] = {
    val classes = element.classList.toSet
    val isOn = on.exists(classes.contains)
    val isOff = off.exists(classes.contains)
    if (isOn) {
      v := true
    } else if (isOff) {
      v := false
    }
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
    v
  }

  override protected def measuredWidth: Double = Measurer.measure(element).width
  override protected def measuredHeight: Double = Measurer.measure(element).height
}

object HTMLComponent extends HTMLComponentTheme {
  def create[T <: Element](tagName: String): T = {
    val e = dom.create[T](tagName)
    // TODO: init
    e
  }

  def element(component: Component): html.Element = component.asInstanceOf[HTMLComponent[html.Element]].element
}