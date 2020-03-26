package io.youi.component

import io.youi.Color
import io.youi.component.types.{Clear, Cursor, Display, FloatStyle, Prop, UserSelect, VerticalAlign, WhiteSpace}
import io.youi.paint.Paint
import io.youi.path.Rectangle
import org.scalajs.dom.raw.MutationObserverInit
import org.scalajs.dom.{MutationObserver, html}

import scala.util.Try

class Component(val element: html.Element) {
  lazy val id: Prop[String] = new Prop[String](element.id, element.id_=)
  object classes extends Prop[Set[String]](
    getter = Option(element.getAttribute("class")).getOrElse("").split(' ').toSet,
    setter = values => element.setAttribute("class", values.mkString(" "))
  ) {
    def +=(className: String): Unit = this @= (this() + className)
    def -=(className: String): Unit = this @= (this() - className)
    def toggle(className: String): Prop[Boolean] = new Prop[Boolean](
      getter = get.contains(className),
      setter = b => if (b) this @= get + className else this @= get - className
    )
  }
  lazy val color: Prop[Color] = Prop.stringify(element.style.color, element.style.color_=, Color, Color.Clear)
  lazy val background: Prop[Paint] = Prop.stringify(element.style.background, element.style.background_=, Paint, Paint.none)
  lazy val backgroundColor: Prop[Color] = Prop.stringify(element.style.backgroundColor, element.style.backgroundColor_=, Color, Color.Clear)
  lazy val clear: Prop[Clear] = Prop.stringify(element.style.clear, element.style.clear_=, Clear, Clear.None)
  lazy val cursor: Prop[Cursor] = Prop.stringify(element.style.cursor, element.style.cursor_=, Cursor, Cursor.Default)
  lazy val display: Prop[Display] = Prop.stringify(element.style.display, element.style.display_=, Display, Display.Inherit, measure)
  lazy val floatStyle: Prop[FloatStyle] = Prop.stringify(element.style.cssFloat, element.style.cssFloat_=, FloatStyle, FloatStyle.None)
  lazy val opacity: Prop[Double] = new Prop[Double](Try(element.style.opacity.toDouble).getOrElse(1.0), d => element.style.opacity = d.toString)
  lazy val userSelect: Prop[UserSelect] = Prop.stringify(element.style.getPropertyValue("user-select"), element.style.setProperty("user-select", _), UserSelect, UserSelect.Initial)
  lazy val verticalAlign: Prop[VerticalAlign] = Prop.stringify(element.style.verticalAlign, element.style.verticalAlign_=, VerticalAlign, VerticalAlign.Unset)
  lazy val whiteSpace: Prop[WhiteSpace] = Prop.stringify(element.style.whiteSpace, element.style.whiteSpace_=, WhiteSpace, WhiteSpace.Normal, measure)

  def measure(): Unit = {}

  /**
    * Returns the absolute bounding rectangle for this element
    */
  def absoluteBounding: Rectangle = {
    val r = element.getBoundingClientRect()
    Rectangle(x = r.left, y = r.top, width = r.width, height = r.height)
  }
}