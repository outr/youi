package io.youi.component
import io.youi.Context
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

  override def draw(context: Context): Unit = {
    super.draw(context)

    scribe.info(s"Draw text! ${value()}")
  }
}

object BasicText extends BasicTextTheme