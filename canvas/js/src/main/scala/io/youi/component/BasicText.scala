package io.youi.component
import io.youi.{Color, Context, Size}
import io.youi.paint.Paint
import io.youi.theme.BasicTextTheme
import reactify.Var

import scala.concurrent.Future

class BasicText extends Component {
  override lazy val theme: Var[BasicTextTheme] = Var(BasicText)

  lazy val value: Var[String] = prop("", updatesRendering = true)
  val breakWords: Var[Boolean] = prop(theme.breakWords, updatesRendering = true)
  object dropShadow {
    val enabled: Var[Boolean] = prop(theme.dropShadow.enabled, updatesRendering = true)
    val angle: Var[Double] = prop(theme.dropShadow.angle, updatesRendering = true)
    val blur: Var[Double] = prop(theme.dropShadow.blur, updatesRendering = true)
    val color: Var[Color] = prop(theme.dropShadow.color, updatesRendering = true)
    val distance: Var[Double] = prop(theme.dropShadow.distance, updatesRendering = true)
  }
  val fill: Var[Paint] = prop(theme.fill, updatesRendering = true)
  object font {
    val family: Var[String] = prop(theme.font.family, updatesRendering = true)
    val size: Var[Double] = prop(theme.font.size, updatesRendering = true)
    val style: Var[String] = prop(theme.font.style, updatesRendering = true)
    val variant: Var[String] = prop(theme.font.variant, updatesRendering = true)
    val weight: Var[String] = prop(theme.font.weight, updatesRendering = true)
  }
  val letterSpacing: Var[Double] = prop(theme.letterSpacing, updatesRendering = true)
  val lineJoin: Var[String] = prop(theme.lineJoin, updatesRendering = true)
  val miterLimit: Var[Double] = prop(theme.miterLimit, updatesRendering = true)
  val padding: Var[Double] = prop(theme.padding, updatesRendering = true)
  val stroke: Var[Color] = prop(theme.stroke, updatesRendering = true)
  val strokeThickness: Var[Double] = prop(theme.strokeThickness, updatesRendering = true)
  val textBaseline: Var[String] = prop(theme.textBaseline, updatesRendering = true)
  val wordWrap: Var[Boolean] = prop(theme.wordWrap, updatesRendering = true)

  val lineHeight: Var[Double] = prop(0.0, updatesRendering = true)
  val wordWrapWidth: Var[Double] = prop(size.width(), updatesRendering = true)

  dropShadow
  font

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

  override def draw(context: Context): Future[Unit] = {
    super.draw(context)

    if (value().nonEmpty) {
      context.setFont(font.family(), font.size(), font.style(), font.variant(), font.weight())
      context.fill(fill(), apply = false)
      context.fillText(value())
    }

    Future.successful(())
  }
}

object BasicText extends BasicTextTheme {
  private val temp = Size.mutable()
}