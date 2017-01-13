package io.youi.html

import com.outr.reactify.{Channel, Var}
import com.outr.scribe.Logging
import io.youi.AbstractComponent
import org.scalajs.dom._
import org.scalajs.dom.html.Element
import org.scalajs.dom.raw.Event

import scala.collection.mutable.ListBuffer

trait Component extends AbstractComponent with Logging {
  protected[youi] val element: Element

  protected def prop[T](get: => T, set: T => Unit, mayCauseResize: Boolean): Var[T] = {
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
    size.width.attach(d => element.style.width = s"${d}px")
    size.height.attach(d => element.style.height = s"${d}px")
    rotation.attach(d => updateTransform())
    scale.x.attach(d => updateTransform())
    scale.y.attach(d => updateTransform())
    opacity.attach(d => element.style.opacity = d.toString)
    visible.attach(b => element.style.visibility = if (b) "visible" else "hidden")
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
}
