package io.youi.gui

import io.youi.{Color, dom}
import org.scalajs.dom.{document, html}
import reactify.standard.StandardVar
import reactify.{Dep, Val, Var}

import scala.util.matching.Regex

class Component(val element: html.Element) {
  lazy val id: Prop[String] = new Prop[String](element.id, element.id_=)
  lazy val classes: Prop[Set[String]] = new Prop[Set[String]](
    get = Option(element.getAttribute("class")).getOrElse("").split(' ').toSet,
    set = values => element.setAttribute("class", values.mkString(" "))
  )
  lazy val content: Prop[String] = new Prop[String](element.innerHTML, element.innerHTML_=, measure)
  lazy val color: Prop[Option[Color]] = new Prop[Option[Color]](
    get = Color.unapply(element.style.color),
    set = o => element.style.color = o.map(_.toRGBA).getOrElse("")
  )
  lazy val backgroundColor: Prop[Option[Color]] = new Prop[Option[Color]](
    get = Color.unapply(element.style.color),
    set = o => element.style.backgroundColor = o.map(_.toRGBA).getOrElse("")
  )

  protected def measure(): Unit = {}
}

class Text(element: html.Element = dom.create.span) extends Component(element) with FontSupport {

}

class Prop[T](get: => T, set: T => Unit, callbacks: (() => Unit)*) extends StandardVar[T](get, Var.Mode.Normal, None) {
  refresh()
  attach { v =>
    set(v)
    callbacks.foreach(_())
  }

  def refresh(): Unit = this @= get
}

trait FontSupport {
  this: Component =>

  object font {
    lazy val family: Prop[String] = new Prop[String](element.style.fontFamily, element.style.fontFamily_=, measure)
    lazy val weight: Prop[String] = new Prop[String](element.style.fontWeight, element.style.fontWeight_=, measure)
    lazy val size: SizeProperty = new SizeProperty(element.style.fontSize, element.style.fontSize_=, measure)
  }
}

trait SizeSupport {
  this: Component =>

  object size {
    lazy val width: SizeProperty = new SizeProperty(element.style.width, element.style.width_=)
    lazy val height: SizeProperty = new SizeProperty(element.style.height, element.style.height_=)

    lazy val center: Val[Double] = Val(width / 2.0)
    lazy val middle: Val[Double] = Val(height / 2.0)
  }
}

class SizeProperty(get: => String, set: String => Unit, callbacks: (() => Unit)*) extends StandardVar[Double](0.0, Var.Mode.Normal, None) {
  val `type`: Var[SizeType] = Var(SizeType.Auto)

  refresh()

  attach { d =>
    if (d != 0.0 && `type`() == SizeType.Auto) {
      `type` @= SizeType.Pixel
    }
    set()
    callbacks.foreach(_())
  }
  `type`.on(set())

  private def set(): Unit = {
    val t = `type`().name
    val value = if (`type`.includeNumeric) {
      val d = apply()
      s"$d$t"
    } else {
      t
    }
    set(value)
  }

  def refresh(): Unit = get match {
    case null | "" | "auto" => {
      this @= 0.0
      `type` @= SizeType.Auto
    }
    case "initial" => {
      this @= 0.0
      `type` @= SizeType.Initial
    }
    case "inherit" => {
      this @= 0.0
      `type` @= SizeType.Inherit
    }
    case SizeProperty.ValueRegex(number, unit) => {
      this @= number.toDouble
      `type` @= SizeType(unit)
    }
  }
}

object SizeProperty {
  val ValueRegex: Regex = """([0-9.]+)(ch|em|ex|rem|vh|vw|vmin|vmax|px|cm|mm|in|pc|pt)""".r
}

sealed abstract class SizeType(val name: String, val includeNumeric: Boolean = true)

object SizeType {
  case object Auto extends SizeType("auto", includeNumeric = false)
  case object Inherit extends SizeType("inherit", includeNumeric = false)
  case object Initial extends SizeType("initial", includeNumeric = false)
  case object Ch extends SizeType("ch")
  case object Em extends SizeType("em")
  case object Ex extends SizeType("ex")
  case object Rem extends SizeType("rem")
  case object ViewportHeight extends SizeType("vh")
  case object ViewportWidth extends SizeType("vw")
  case object ViewportMinimum extends SizeType("vmin")
  case object ViewportMaximum extends SizeType("vmax")
  case object Pixel extends SizeType("px")
  case object Centimeter extends SizeType("cm")
  case object Millimeter extends SizeType("mm")
  case object Inch extends SizeType("in")
  case object Pica extends SizeType("pc")
  case object Point extends SizeType("pt")

  private val map = List(
    Auto, Inherit, Initial, Ch, Em, Ex, Rem, ViewportHeight, ViewportWidth, ViewportMinimum, ViewportMaximum, Pixel,
    Centimeter, Millimeter, Inch, Pica, Point
  ).map(t => t.name -> t).toMap

  def apply(name: String): SizeType = map.getOrElse(name.toLowerCase, Pixel)
}

sealed abstract class PositionType(val name: String)

object PositionType {
  case object Static extends PositionType("")
  case object Relative extends PositionType("relative")
  case object Absolute extends PositionType("absolute")
  case object Fixed extends PositionType("fixed")
  case object Sticky extends PositionType("sticky")

  private val map = List(Static, Relative, Absolute, Fixed, Sticky).map(t => t.name -> t).toMap

  def apply(name: String): PositionType = map.getOrElse(name.toLowerCase, Static)
}

trait PositionSupport {
  this: Component =>

  object position {
    lazy val `type`: Prop[PositionType] = new Prop[PositionType](
      get = PositionType(element.style.position),
      set = pt => element.style.position = pt.name
    )
    lazy val x: SizeProperty = new SizeProperty(element.style.left, element.style.left_=)
    lazy val y: SizeProperty = new SizeProperty(element.style.top, element.style.top_=)
    lazy val z: Prop[Int] = new Prop[Int](element.style.zIndex.toInt, i => element.style.zIndex = i.toString)

    lazy val left: Var[Double] = x
    lazy val center: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(left)(_ + (c.measured.width / 2.0), _ - (c.measured.width / 2.0))
      case c: SizeSupport => Dep(left)(_ + (c.size.width / 2.0), _ - (c.size.width / 2.0))
      case _ => Dep(left)(r => r, r => r)
    }
    lazy val right: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(left)(_ + c.measured.width, _ - c.measured.width)
      case c: SizeSupport => Dep(left)(_ + c.size.width, _ - c.size.width)
      case _ => Dep(left)(r => r, r => r)
    }

    lazy val top: Var[Double] = y
    lazy val middle: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(top)(_ + (c.measured.height / 2.0), _ - (c.measured.height / 2.0))
      case c: SizeSupport => Dep(top)(_ + (c.size.height / 2.0), _ - (c.size.height / 2.0))
      case _ => Dep(top)(identity, identity)
    }
    lazy val bottom: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(top)(_ + c.measured.height, _ - c.measured.height)
      case c: SizeSupport => Dep(top)(_ + c.size.height, _ - c.size.height)
      case _ => Dep(top)(identity, identity)
    }

    lazy val depth: Var[Int] = z
  }
}

trait MeasuredSupport {
  this: Component =>

  protected val x: Var[Double] = Var(0.0)
  protected val y: Var[Double] = Var(0.0)
  protected val w: Var[Double] = Var(0.0)
  protected val h: Var[Double] = Var(0.0)

  object measured {
    lazy val x: Val[Double] = x
    lazy val y: Val[Double] = y
    lazy val width: Val[Double] = w
    lazy val height: Val[Double] = h
  }

  override protected def measure(): Unit = {
    Measurer.measureHTML(element.outerHTML, element.style.width, element.style.height, x, y, w, h)
  }
}

object Measurer {
  private lazy val container = {
    val span = dom.create[html.Span]("span")
    span.style.position = "absolute"
    span.style.visibility = "hidden"
    span.style.width = "auto"
    span.style.height = "auto"
    document.body.appendChild(span)
    span
  }

  def measureHTML(htmlString: String, width: String, height: String, x: Var[Double], y: Var[Double], w: Var[Double], h: Var[Double]): Unit = {
    container.innerHTML = htmlString
    val e = container.firstElementChild.asInstanceOf[html.Element]
    e.style.width = width
    if (width != "auto") e.style.display = "block"
    e.style.height = height
    e.style.position = "static"

    val bounding = e.getBoundingClientRect()
    container.innerHTML = ""
    x @= bounding.left
    y @= bounding.top
    w @= bounding.width
    h @= bounding.height
  }
}