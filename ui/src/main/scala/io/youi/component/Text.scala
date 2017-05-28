package io.youi.component

import io.youi.component.font.{Font, TextPaths}
import io.youi.component.shape.Drawable
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

  def textPaths: Option[TextPaths] = drawable() match {
    case tp: TextPaths => Some(tp)
    case _ => None
  }

  override protected def defaultTheme = Text

  protected def updateDrawable(): Drawable = {
    if (value().nonEmpty && font.file.loaded()) {
      try {
        val textPaths = font.file().createPaths(value(), font.size(), font.kerning())
        size.measured.width := textPaths.boundingBox.width
        size.measured.height := textPaths.boundingBox.height
        textPaths.zero()
      } catch {
        case t: Throwable => {
          scribe.error(t)
          throw t
        }
      }
    } else {
      Drawable.empty
    }
  }
}

object Text extends Theme(DrawableComponent)