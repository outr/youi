package youi.component.feature

import youi.Color
import youi.component.types.{Prop, TextDecorationLine, TextDecorationStyle}

class TextDecorationFeature(override val parent: FeatureParent) extends Feature {
  lazy val line: Prop[Set[TextDecorationLine]] = new Prop(
    getter = Set.empty,
    setter = set => parent.css.setProperty("text-decoration-line", set.map(TextDecorationLine.toString).mkString(" "))
  )
  lazy val color: Prop[Color] = Prop.stringify(parent.css.getPropertyValue("text-decoration-color"), parent.css.setProperty("text-decoration-color", _), Color, Color.Clear)
  lazy val style: Prop[TextDecorationStyle] = Prop.stringify(parent.css.getPropertyValue("text-decoration-style"), parent.css.setProperty("text-decoration-style", _), TextDecorationStyle, TextDecorationStyle.Initial)
}
