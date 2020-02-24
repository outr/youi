package io.youi.component

import io.youi.Color
import io.youi.component.types.{Cursor, Display, Prop, UserSelect, WhiteSpace}
import io.youi.paint.Paint
import org.scalajs.dom.html

class Component(val element: html.Element) {
  lazy val id: Prop[String] = new Prop[String](element.id, element.id_=)
  lazy val classes: Prop[Set[String]] = new Prop[Set[String]](
    get = Option(element.getAttribute("class")).getOrElse("").split(' ').toSet,
    set = values => element.setAttribute("class", values.mkString(" "))
  )
  lazy val content: Prop[String] = new Prop[String](element.innerHTML, element.innerHTML_=, measure)
  lazy val color: Prop[Color] = Prop.stringify(element.style.color, element.style.color_=, Color, Color.Clear)
  lazy val background: Prop[Paint] = Prop.stringify(element.style.background, element.style.background_=, Paint, Paint.none)
  lazy val backgroundColor: Prop[Color] = Prop.stringify(element.style.backgroundColor, element.style.backgroundColor_=, Color, Color.Clear)
  lazy val display: Prop[Display] = Prop.stringify(element.style.display, element.style.display_=, Display, Display.Inherit, measure)
  lazy val cursor: Prop[Cursor] = Prop.stringify(element.style.cursor, element.style.cursor_=, Cursor, Cursor.Default)
  lazy val whiteSpace: Prop[WhiteSpace] = Prop.stringify(element.style.whiteSpace, element.style.whiteSpace_=, WhiteSpace, WhiteSpace.Normal, measure)
  lazy val userSelect: Prop[UserSelect] = Prop.stringify(element.style.getPropertyValue("user-select"), element.style.setProperty("user-select", _), UserSelect, UserSelect.Initial)

  protected def measure(): Unit = {}
}