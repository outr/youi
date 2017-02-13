package io.youi.hypertext

import com.outr.reactify.{Channel, Var}
import scribe.Logging
import io.youi.AbstractComponent
import io.youi.hypertext.style.Color
import org.scalajs.dom.{Event, _}
import org.scalajs.dom.html.Element
import org.scalajs.dom.raw.Event

import scala.collection.mutable.ListBuffer

trait Component extends AbstractComponent with Logging {
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

    position.`type`.attachAndFire(p => element.style.position = p.toString.toLowerCase)
    position.x.attach(d => element.style.left = s"${d}px")
    position.y.attach(d => element.style.top = s"${d}px")
    size.width.attach(s => element.style.width = s.toString)
    size.height.attach(s => element.style.height = s.toString)
    rotation.attach(d => updateTransform())
    scale.x.attach(d => updateTransform())
    scale.y.attach(d => updateTransform())
    opacity.attach(d => element.style.opacity = d.toString)
    visible.attach(b => element.style.visibility = if (b) "visible" else "hidden")
    color.red.attach(d => updateColor())
    color.green.attach(d => updateColor())
    color.blue.attach(d => updateColor())
    color.alpha.attach(d => updateColor())
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
}
