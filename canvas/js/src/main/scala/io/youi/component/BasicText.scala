package io.youi.component

import io.youi.{Color, Context, Size}
import io.youi.paint.Paint
import io.youi.theme.BasicTextTheme
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

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
      this.size.measured.width := size.width
      this.size.measured.height := size.height
    }
  }

  override def draw(context: Context): Unit = {
    super.draw(context)

    if (value().nonEmpty) {
      context.setFont(font.family(), font.size(), font.style(), font.variant(), font.weight())
      context.fill(fill(), apply = false)
      context.fillText(value())
    }
  }
}

object BasicText extends BasicTextTheme {
  private val temp = Size.mutable()
}