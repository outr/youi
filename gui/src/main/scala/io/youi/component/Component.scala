package io.youi.component

import io.youi.Color
import io.youi.component.types.{Cursor, Display, Prop, UserSelect, WhiteSpace}
import io.youi.paint.Paint
import io.youi.path.Rectangle
import org.scalajs.dom.html

import scala.util.Try

class Component(val element: html.Element) {
  lazy val id: Prop[String] = new Prop[String](element.id, element.id_=)
  object classes extends Prop[Set[String]](
    getter = Option(element.getAttribute("class")).getOrElse("").split(' ').toSet,
    setter = values => element.setAttribute("class", values.mkString(" "))
  ) {
    def toggle(className: String): Prop[Boolean] = new Prop[Boolean](
      getter = get.contains(className),
      setter = b => if (b) this @= get + className else this @= get - className
    )
  }
  lazy val content: Prop[String] = new Prop[String](element.innerHTML, element.innerHTML_=, measure)
  lazy val color: Prop[Color] = Prop.stringify(element.style.color, element.style.color_=, Color, Color.Clear)
  lazy val background: Prop[Paint] = Prop.stringify(element.style.background, element.style.background_=, Paint, Paint.none)
  lazy val backgroundColor: Prop[Color] = Prop.stringify(element.style.backgroundColor, element.style.backgroundColor_=, Color, Color.Clear)
  lazy val cursor: Prop[Cursor] = Prop.stringify(element.style.cursor, element.style.cursor_=, Cursor, Cursor.Default)
  lazy val display: Prop[Display] = Prop.stringify(element.style.display, element.style.display_=, Display, Display.Inherit, measure)
  lazy val opacity: Prop[Double] = new Prop[Double](Try(element.style.opacity.toDouble).getOrElse(1.0), d => element.style.opacity = d.toString)
  lazy val userSelect: Prop[UserSelect] = Prop.stringify(element.style.getPropertyValue("user-select"), element.style.setProperty("user-select", _), UserSelect, UserSelect.Initial)
  lazy val whiteSpace: Prop[WhiteSpace] = Prop.stringify(element.style.whiteSpace, element.style.whiteSpace_=, WhiteSpace, WhiteSpace.Normal, measure)

  protected def measure(): Unit = {}

  /**
    * Returns the absolute bounding rectangle for this element
    */
  def absoluteBounding: Rectangle = {
    val r = element.getBoundingClientRect()
    Rectangle(x = r.left, y = r.top, width = r.width, height = r.height)
  }
}