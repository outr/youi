package io.youi.component
import io.youi.{Context, Size}
import io.youi.paint.Paint
import io.youi.theme.BasicTextTheme
import reactify.Var

class BasicText extends Component {
  override lazy val theme: Var[BasicTextTheme] = Var(BasicText)

  lazy val value: Var[String] = prop("", updatesRendering = true)
  val fill: Var[Paint] = prop(theme.fill, updatesRendering = true)
  object font {
    val family: Var[String] = prop(theme.font.family, updatesRendering = true)
    val size: Var[Double] = prop(theme.font.size, updatesRendering = true)
    val style: Var[String] = prop(theme.font.style, updatesRendering = true)
    val variant: Var[String] = prop(theme.font.variant, updatesRendering = true)
    val weight: Var[String] = prop(theme.font.weight, updatesRendering = true)
  }

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