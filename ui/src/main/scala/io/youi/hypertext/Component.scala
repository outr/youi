package io.youi.hypertext

import reactify.{Channel, Var}
import io.youi.AbstractComponent
import org.scalajs.dom._
import org.scalajs.dom.html.Element
import org.scalajs.dom.raw.Event

import scala.collection.mutable.ListBuffer

trait Component extends AbstractComponent {
  protected[youi] val element: Element

  lazy val click: Channel[Event] = events("click", stopPropagation = false)

  protected[hypertext] def prop[T](get: => T, set: T => Unit, mayCauseResize: Boolean): Var[T] = {
    val v = Var[T](get)
    v.attach { value =>
      set(value)
      if (mayCauseResize) {
        onNextFrame(updateSize())
      }
    }
    v
  }

  protected def onNextFrame(f: => Unit): Unit = {
    window.requestAnimationFrame((d: Double) => {
      f
    })
  }

  protected def events(eventType: String, stopPropagation: Boolean): Channel[Event] = {
    val channel = Channel[Event]
    element.addEventListener(eventType, (evt: Event) => {
      if (stopPropagation) {
        evt.preventDefault()
        evt.stopPropagation()
      }
      channel := evt
    })
    channel
  }

  override protected def init(): Unit = {
    super.init()

    opacity := (try {
      element.style.opacity.toDouble
    } catch {
      case t: Throwable => 1.0
    })
    visible := element.style.visibility != "hidden"

    position.`type`.attachAndFire(p => element.style.position = p.toString.toLowerCase)
    position.x.attach(d => element.style.left = s"${d}px")
    position.y.attach(d => element.style.top = s"${d}px")
    size.width.attach { d =>
      element.style.width = s"${d}px"
      onNextFrame(updateSize())
    }
    size.height.attach { d =>
      element.style.height = s"${d}px"
      onNextFrame(updateSize())
    }
    parent.attach { p =>
      onNextFrame(updateSize())
    }
    rotation.attach(d => updateTransform())
    scale.x.attach(d => updateTransform())
    scale.y.attach(d => updateTransform())
    opacity.attach(d => element.style.opacity = d.toString)
    visible.attach(b => element.style.visibility = if (b) "visible" else "hidden")
    color.red.attach(d => updateColor())
    color.green.attach(d => updateColor())
    color.blue.attach(d => updateColor())
    color.alpha.attach(d => updateColor())
    backgroundColor.red.attach(d => updateBackgroundColor())
    backgroundColor.green.attach(d => updateBackgroundColor())
    backgroundColor.blue.attach(d => updateBackgroundColor())
    backgroundColor.alpha.attach(d => updateBackgroundColor())
    updateSize()
  }

  protected def updateSize(): Unit = {
    if (actualWidth() != element.offsetWidth) actualWidth.setStatic(element.offsetWidth)
    if (actualHeight() != element.offsetHeight) actualHeight.setStatic(element.offsetHeight)
  }

  protected def updateTransform(): Unit = {
    val b = ListBuffer.empty[String]
    if (rotation() != 0.0) {
      b += s"rotate(${rotation() * 360.0}deg)"
    }
    if (scale.x() != 1.0) {
      b += s"scaleX(${scale.x()})"
    }
    if (scale.y() != 1.0) {
      b += s"scaleY(${scale.y()})"
    }
    element.style.transform = b.mkString(" ")
  }

  protected def updateColor(): Unit = {
    def i(d: Double): Int = math.round(d * 255.0).toInt
    element.style.color = s"rgba(${i(color.red())}, ${i(color.green())}, ${i(color.blue())}, ${color.alpha()})"
  }

  protected def updateBackgroundColor(): Unit = {
    def i(d: Double): Int = math.round(d * 255.0).toInt
    element.style.backgroundColor = s"rgba(${i(backgroundColor.red())}, ${i(backgroundColor.green())}, ${i(backgroundColor.blue())}, ${backgroundColor.alpha()})"
  }
}

object Component {
  private var cache = Map.empty[html.Element, Component]

  def cached[E <: html.Element, T <: Component](element: E, create: E => T): T = synchronized {
    cache.get(element) match {
      case Some(c) => c.asInstanceOf[T]
      case None => {
        val c = create(element)
        cache += element -> c
        c
      }
    }
  }
}