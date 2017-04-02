package io.youi.hypertext

import io.youi.hypertext.border.ComponentBorders
import io.youi.hypertext.style.{ComponentOverflow, Overflow}
import reactify.{Channel, State, Val, Var}
import io.youi.{AbstractComponent, Color}
import org.scalajs.dom._
import org.scalajs.dom.html.Element
import org.scalajs.dom.raw.Event

import scala.collection.mutable.ListBuffer

trait Component extends AbstractComponent {
  protected[youi] val element: Element

  object event {
    lazy val change: Channel[Event] = events("change")
    lazy val click: Channel[MouseEvent] = events("click")
    lazy val doubleClick: Channel[MouseEvent] = events("dblclick")
    lazy val contextMenu: Channel[MouseEvent] = events("contextmenu")
    lazy val focus: Channel[FocusEvent] = events("focus")
    lazy val blur: Channel[FocusEvent] = events("blur")
    object key {
      lazy val down: Channel[KeyboardEvent] = events("keydown")
      lazy val press: Channel[KeyboardEvent] = events("keypress")
      lazy val up: Channel[KeyboardEvent] = events("keyup")
    }
    object mouse {
      lazy val enter: Channel[MouseEvent] = events("mouseenter")
      lazy val over: Channel[MouseEvent] = events("mouseover")
      lazy val move: Channel[MouseEvent] = events("mousemove")
      lazy val down: Channel[MouseEvent] = events("mousedown")
      lazy val up: Channel[MouseEvent] = events("mouseup")
      lazy val leave: Channel[MouseEvent] = events("mouseleave")
      lazy val out: Channel[MouseEvent] = events("mouseout")
      lazy val wheel: Channel[MouseEvent] = events("wheel")
    }
  }

  lazy val border: ComponentBorders = new ComponentBorders(this)
  lazy val overflow: ComponentOverflow = new ComponentOverflow(this)

  def focus(): Unit = element.focus()
  def blur(): Unit = element.blur()
  def click(): Unit = element.click()

  protected[hypertext] def prop[T](get: => T, set: T => Unit, mayCauseResize: Boolean): Var[T] = {
    val v = Var[T](get)
    v.attach { value =>
      set(value)
      if (mayCauseResize) {
        nextFrame(updateSize())
      }
    }
    v
  }

  protected def events[E <: Event](eventType: String, stopPropagation: Boolean = false): Channel[E] = {
    val channel = Channel[E]
    element.addEventListener(eventType, (evt: E) => {
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
      nextFrame(updateSize())
    }
    size.height.attach { d =>
      element.style.height = s"${d}px"
      nextFrame(updateSize())
    }
    size.min.width.attach { d =>
      element.style.minWidth = s"${d}px"
      nextFrame(updateSize())
    }
    size.min.height.attach { d =>
      element.style.minHeight = s"${d}px"
      nextFrame(updateSize())
    }
    size.max.width.attach { d =>
      element.style.maxWidth = s"${d}px"
      nextFrame(updateSize())
    }
    size.max.height.attach { d =>
      element.style.maxHeight = s"${d}px"
      nextFrame(updateSize())
    }
    parent.attach { p =>
      nextFrame(updateSize())
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
    padding.left.attach(d => element.style.paddingLeft = s"${d}px")
    padding.right.attach(d => element.style.paddingRight = s"${d}px")
    padding.top.attach(d => element.style.paddingTop = s"${d}px")
    padding.bottom.attach(d => element.style.paddingBottom = s"${d}px")
    margin.left.attach(d => element.style.marginLeft = s"${d}px")
    margin.right.attach(d => element.style.marginRight = s"${d}px")
    margin.top.attach(d => element.style.marginTop = s"${d}px")
    margin.bottom.attach(d => element.style.marginBottom = s"${d}px")

    element.addEventListener("scroll", (evt: Event) => {
      updateSize()
    })
    scrollbar.vertical.position.attach { p =>
      if (!updatingSize) {
        element.scrollTop = p
        updateSize()
      }
    }
    scrollbar.horizontal.position.attach { p =>
      if (!updatingSize) {
        element.scrollLeft = p
        updateSize()
      }
    }
    scrollbar.vertical.percentage.attach { p =>
      if (!updatingSize) {
        element.scrollTop = (size.inner.height() - size.actual.height()) * p
        updateSize()
      }
    }
    scrollbar.horizontal.percentage.attach { p =>
      if (!updatingSize) {
        element.scrollLeft = (size.inner.width() - size.actual.width()) * p
        updateSize()
      }
    }

    if (!color.isDefault) updateColor()
    if (!backgroundColor.isDefault) updateBackgroundColor()
    updateSize()
  }

  private var updatingSize: Boolean = false

  protected def determineActualWidth: Double = element.offsetWidth + margin.left() + margin.right()
  protected def determineActualHeight: Double = element.offsetHeight + margin.top() + margin.bottom()

  def updateSize(): Unit = if (!updatingSize) {
    updatingSize = true
    try {
      if (actualWidth() != determineActualWidth) actualWidth.setStatic(determineActualWidth)
      if (actualHeight() != determineActualHeight) actualHeight.setStatic(determineActualHeight)

      val h = scrollbar.horizontal.size.asInstanceOf[Var[Double]]
      val v = scrollbar.vertical.size.asInstanceOf[Var[Double]]

      h.set(math.max(0.0, element.offsetHeight - element.clientHeight - border.top.size().getOrElse(0.0) - border.bottom.size().getOrElse(0.0)))
      v.set(math.max(0.0, element.offsetWidth - element.clientWidth - border.left.size().getOrElse(0.0) - border.right.size().getOrElse(0.0)))

      scrollbar.horizontal.position := element.scrollLeft
      scrollbar.vertical.position := element.scrollTop
      innerWidth := element.scrollWidth
      innerHeight := element.scrollHeight

      scrollbar.vertical.percentage := scrollbar.vertical.position / (size.inner.height() - size.actual.height())
      scrollbar.horizontal.percentage := scrollbar.horizontal.position / (size.inner.width - size.actual.height)
    } finally {
      updatingSize = false
    }
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
    element.style.color = Color.toCSS(color.red(), color.green(), color.blue(), color.alpha())
  }

  protected def updateBackgroundColor(): Unit = {
    val css = Color.toCSS(backgroundColor.red(), backgroundColor.green(), backgroundColor.blue(), backgroundColor.alpha())
    element.style.backgroundColor = css
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