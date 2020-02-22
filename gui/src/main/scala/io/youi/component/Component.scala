package io.youi.component

import io.youi.Color
import io.youi.component.types.{Display, Prop}
import org.scalajs.dom.html

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
  lazy val display: Prop[Display] = Prop.stringify(element.style.display, element.style.display_=, Display, Display.Inherit, measure)

  protected def measure(): Unit = {}
}