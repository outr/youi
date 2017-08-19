package io.youi.component

import io.youi.Context
import io.youi.font.TextPaths
import io.youi.theme.TextTheme
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify._

class Text extends Component with TextTheme {
  override lazy val theme: Var[TextTheme] = Var(Text)

  override protected def defaultThemeParent = Some(theme)

  val value: Var[String] = Var("")
  val textPaths: Val[TextPaths] = Val {
    if (value().nonEmpty && font.file.loaded) {
      font
        .file()
        .createPaths(value(), font.size(), font.kerning())
        .zero()
    } else {
      TextPaths(Vector.empty)
    }
  }

  size.measured.width := textPaths.boundingBox.width
  size.measured.height := textPaths.boundingBox.height

  override def draw(context: Context): Unit = {
    super.draw(context)

    if (value().nonEmpty) {
      if (shadow.enabled()) {
        context.setShadow(shadow.blur, shadow.color, shadow.x, shadow.y)
      }
      context.lineJoin(lineJoin())
      context.miterLimit(miterLimit())
      context.textBaseline(textBaseline())
      textPaths().draw(this, context.canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D])
      if (fill.nonEmpty) {
        context.fill(fill(), apply = true)
      }
      if (stroke.nonEmpty) {
        context.stroke(stroke(), apply = true)
      }
    }
  }
}

object Text extends TextTheme