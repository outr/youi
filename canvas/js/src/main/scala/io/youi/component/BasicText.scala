package io.youi.component

import io.youi.{Context, Size}
import io.youi.theme.BasicTextTheme
import reactify.Var

class BasicText extends Component with BasicTextTheme {
  override lazy val theme: Var[BasicTextTheme] = Var(BasicText)

  lazy val value: Var[String] = prop("", updatesRendering = true)

  override protected def defaultThemeParent = Some(theme)

  override protected def reMeasure(context: Context): Unit = {
    super.reMeasure(context)

    if (value().isEmpty) {
      size.measured.width := 0.0
      size.measured.height := 0.0
    } else {
      context.setFont(font.family(), font.size(), font.style(), font.variant(), font.weight())
      val size = context.measureText(value(), BasicText.temp)
      updateMeasured(size.width, size.height)
    }
  }

  override def draw(context: Context): Unit = {
    super.draw(context)

    if (value().nonEmpty) {
      if (shadow.enabled()) {
        context.setShadow(shadow.blur, shadow.color, shadow.x, shadow.y)
      }
      context.lineJoin(lineJoin())
      context.miterLimit(miterLimit())
      context.textBaseline(textBaseline())
      context.setFont(font.family(), font.size(), font.style(), font.variant(), font.weight())
      if (fill.nonEmpty) {
        context.fill(fill(), apply = false)
        context.fillText(value())
      }
      if (stroke.nonEmpty) {
        context.stroke(stroke(), apply = false)
        context.strokeText(value())
      }
    }
  }
}

object BasicText extends BasicTextTheme {
  private val temp = Size.mutable()
}