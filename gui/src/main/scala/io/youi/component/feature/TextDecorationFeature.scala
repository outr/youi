package io.youi.component.feature

import io.youi.Color
import io.youi.component.Component
import io.youi.component.types.{Prop, TextDecorationLine, TextDecorationStyle}

class TextDecorationFeature(override val component: Component) extends Feature {
  lazy val line: Prop[Set[TextDecorationLine]] = new Prop(
    getter = Set.empty,
    setter = set => component.element.style.setProperty("text-decoration-line", set.map(TextDecorationLine.toString).mkString(" "))
  )
  lazy val color: Prop[Color] = Prop.stringify(component.style.getPropertyValue("text-decoration-color"), component.style.setProperty("text-decoration-color", _), Color, Color.Clear)
  lazy val style: Prop[TextDecorationStyle] = Prop.stringify(component.style.getPropertyValue("text-decoration-style"), component.style.setProperty("text-decoration-style", _), TextDecorationStyle, TextDecorationStyle.Initial)
}
