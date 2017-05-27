package io.youi.component

import io.youi.component.font.Font
import io.youi.component.shape.{Drawable, Group, Translate}
import io.youi.style.Theme
import reactify.Var

class Text extends DrawableComponent {
  val value: Var[String] = Var("")
  object font {
    val file: Var[Font] = Var(theme.font.file)
    val size: Var[Double] = Var(theme.font.size)
    val kerning: Var[Boolean] = Var(theme.font.kerning)
  }

  drawable := updateDrawable()

  override protected def defaultTheme = Text

  protected def updateDrawable(): Drawable = {
    if (value().nonEmpty && font.file.loaded()) {
      val path = font.file().createPath(value(), font.size(), font.kerning())
      val translate = Translate(path.adjustX, path.height + path.adjustY)
      size.measured.width := path.width
      size.measured.height := path.height
      Group(
        translate,
        path.path
      )
    } else {
      Drawable.empty
    }
  }
}

object Text extends Theme(DrawableComponent)