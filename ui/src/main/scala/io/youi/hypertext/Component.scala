package io.youi.hypertext

import reactify.{Channel, Var}
import io.youi.{AbstractComponent, Color}
import org.scalajs.dom._
import org.scalajs.dom.html.Element
import org.scalajs.dom.raw.Event

import scala.collection.mutable.ListBuffer

trait Component extends AbstractComponent {
  protected[youi] val element: Element

  lazy val click: Channel[Event] = events("click", stopPropagation = false)

  object border {
    private lazy val topLeftRadius = prop[Double](0.0, d => element.style.borderTopLeftRadius = s"${d}px", mayCauseResize = true)
    private lazy val topRightRadius = prop[Double](0.0, d => element.style.borderTopRightRadius = s"${d}px", mayCauseResize = true)
    private lazy val bottomLeftRadius = prop[Double](0.0, d => element.style.borderBottomLeftRadius = s"${d}px", mayCauseResize = true)
    private lazy val bottomRightRadius = prop[Double](0.0, d => element.style.borderBottomRightRadius = s"${d}px", mayCauseResize = true)

    case object top extends Border(Component.this, element.style.borderTopColor = _, element.style.borderTopStyle = _, element.style.borderTopWidth = _) {
      object left {
        def radius: Var[Double] = topLeftRadius
      }
      object right {
        def radius: Var[Double] = topRightRadius
      }
    }
    case object bottom extends Border(Component.this, element.style.borderBottomColor = _, element.style.borderBottomStyle = _, element.style.borderBottomWidth = _) {
      object left {
        def radius: Var[Double] = bottomLeftRadius
      }
      object right {
        def radius: Var[Double] = bottomRightRadius
      }
    }
    case object left extends Border(Component.this, element.style.borderLeftColor = _, element.style.borderLeftStyle = _, element.style.borderLeftWidth = _) {
      object top {
        def radius: Var[Double] = topLeftRadius
      }
      object bottom {
        def radius: Var[Double] = bottomLeftRadius
      }
    }
    case object right extends Border(Component.this, element.style.borderRightColor = _, element.style.borderRightStyle = _, element.style.borderRightWidth = _) {
      object top {
        def radius: Var[Double] = topRightRadius
      }
      object bottom {
        def radius: Var[Double] = bottomRightRadius
      }
    }

    object color {
      def :=(value: Option[Color]): Unit = set(value)
      def set(value: Option[Color]): Unit = {
        top.color := value
        bottom.color := value
        left.color := value
        right.color := value
      }
    }
    object style {
      def :=(value: Option[BorderStyle]): Unit = set(value)
      def set(value: Option[BorderStyle]): Unit = {
        top.style := value
        bottom.style := value
        left.style := value
        right.style := value
      }
    }
    object width {
      def :=(value: Option[Double]): Unit = set(value)
      def set(value: Option[Double]): Unit = {
        top.width := value
        bottom.width := value
        left.width := value
        right.width := value
      }
    }
    object radius {
      def :=(value: Double): Unit = set(value)
      def set(value: Double): Unit = {
        topLeftRadius := value
        topRightRadius := value
        bottomLeftRadius := value
        bottomRightRadius := value
      }
    }
  }

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
    if (!color.isDefault) updateColor()
    if (!backgroundColor.isDefault) updateBackgroundColor()
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

class Border(component: Component,
             updateColor: String => Unit,
             updateStyle: String => Unit,
             updateWidth: String => Unit) {
  lazy val color: Var[Option[Color]] = component.prop(None, o => updateColor(o.map(_.toCSS).getOrElse("")), mayCauseResize = false)
  lazy val style: Var[Option[BorderStyle]] = component.prop(None, o => updateStyle(o.map(_.value).getOrElse("")), mayCauseResize = true)
  lazy val width: Var[Option[Double]] = component.prop(None, o => updateWidth(o.map(d => s"${d}px").getOrElse("")), mayCauseResize = true)
}

sealed abstract class BorderStyle(val value: String)

object BorderStyle {
  case object None extends BorderStyle("none")
  case object Hidden extends BorderStyle("hidden")
  case object Dotted extends BorderStyle("dotted")
  case object Dashed extends BorderStyle("dashed")
  case object Solid extends BorderStyle("solid")
  case object Double extends BorderStyle("double")
  case object Groove extends BorderStyle("groove")
  case object Ridge extends BorderStyle("ridge")
  case object Inset extends BorderStyle("inset")
  case object Outset extends BorderStyle("outset")
}