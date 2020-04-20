package io.youi.component.feature

import io.youi.Color
import io.youi.component.Component
import io.youi.component.types.{Prop, TextDecorationLine, TextDecorationStyle}
import io.youi.theme.Theme

import scala.scalajs.js.|

class TextDecorationFeature(override val parent: FeatureParent) extends Feature {
  lazy val line: Prop[Set[TextDecorationLine]] = new Prop(
    getter = Set.empty,
    setter = set => parent.css.setProperty("text-decoration-line", set.map(TextDecorationLine.toString).mkString(" "))
  )
  lazy val color: Prop[Color] = Prop.stringify(parent.css.getPropertyValue("text-decoration-color"), parent.css.setProperty("text-decoration-color", _), Color, Color.Clear)
  lazy val style: Prop[TextDecorationStyle] = Prop.stringify(parent.css.getPropertyValue("text-decoration-style"), parent.css.setProperty("text-decoration-style", _), TextDecorationStyle, TextDecorationStyle.Initial)
}
