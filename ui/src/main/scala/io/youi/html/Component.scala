package io.youi.html

import com.outr.props.{Channel, Var}
import com.outr.scribe.Logging
import io.youi.AbstractComponent
import org.scalajs.dom._
import org.scalajs.dom.html.Element
import org.scalajs.dom.raw.Event

trait Component extends AbstractComponent with Logging {
  protected[youi] val element: Element

  protected def prop[T](get: => T, set: T => Unit, mayCauseResize: Boolean = false): Var[T] = {
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
    position.x.attachAndFire(d => element.style.left = s"${d}px")
    position.y.attachAndFire(d => element.style.top = s"${d}px")
    size.width.attach(d => element.style.width = s"${d}px")
    size.height.attach(d => element.style.height = s"${d}px")
    updateSize()
  }

  protected def updateSize(): Unit = {
    actualWidth := element.offsetWidth
    actualHeight := element.offsetHeight
  }
}
