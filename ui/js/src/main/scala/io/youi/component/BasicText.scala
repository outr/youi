package io.youi.component

import com.outr.pixijs.PIXI
import io.youi._
import io.youi.style.Paint
import io.youi.theme.BasicTextTheme
import reactify._

class BasicText extends Component {
  override protected[component] lazy val instance: PIXI.Text = new PIXI.Text("")

  override lazy val theme: Var[BasicTextTheme] = Var(BasicText)

  lazy val value: Var[String] = prop(instance.text, (s: String) => instance.text = s, updatesRendering = true)

  val breakWords: Var[Boolean] = prop(theme.breakWords, (b: Boolean) => instance.style.breakWords = b, updatesRendering = true)
  object dropShadow {
    val enabled: Var[Boolean] = prop(theme.dropShadow.enabled, (b: Boolean) => instance.style.dropShadow = b, updatesRendering = true)
    val angle: Var[Double] = prop(theme.dropShadow.angle, (d: Double) => instance.style.dropShadowAngle = d, updatesRendering = true)
    val blur: Var[Double] = prop(theme.dropShadow.blur, (d: Double) => instance.style.dropShadowBlur = d, updatesRendering = true)
    val color: Var[Color] = prop(theme.dropShadow.color, (c: Color) => instance.style.dropShadowColor = c.hex, updatesRendering = true)
    val distance: Var[Double] = prop(theme.dropShadow.distance, (d: Double) => instance.style.dropShadowDistance = d, updatesRendering = true)
  }
  val fill: Var[Paint] = prop(theme.fill, (f: Paint) => instance.style.fill = f(this), updatesRendering = true)
  object font {
    val family: Var[String] = prop(theme.font.family, (s: String) => instance.style.fontFamily = s, updatesRendering = true)
    val size: Var[Double] = prop(theme.font.size, (d: Double) => instance.style.fontSize = d, updatesRendering = true)
    val style: Var[String] = prop(theme.font.style, (s: String) => instance.style.fontStyle = s, updatesRendering = true)
    val variant: Var[String] = prop(theme.font.variant, (s: String) => instance.style.fontVariant = s, updatesRendering = true)
    val weight: Var[String] = prop(theme.font.weight, (s: String) => instance.style.fontWeight = s, updatesRendering = true)
  }
  val letterSpacing: Var[Double] = prop(theme.letterSpacing, (d: Double) => instance.style.letterSpacing = d, updatesRendering = true)
  val lineJoin: Var[String] = prop(theme.lineJoin, (s: String) => instance.style.lineJoin = s, updatesRendering = true)
  val miterLimit: Var[Double] = prop(theme.miterLimit, (d: Double) => instance.style.miterLimit = d, updatesRendering = true)
  val padding: Var[Double] = prop(theme.padding, (d: Double) => instance.style.padding = d, updatesRendering = true)
  val stroke: Var[Color] = prop(theme.stroke, (c: Color) => instance.style.stroke = c.hex, updatesRendering = true)
  val strokeThickness: Var[Double] = prop(theme.strokeThickness, (d: Double) => instance.style.strokeThickness = d, updatesRendering = true)
  val textBaseline: Var[String] = prop(theme.textBaseline, (s: String) => instance.style.textBaseline = s, updatesRendering = true)
  val wordWrap: Var[Boolean] = prop(theme.wordWrap, (b: Boolean) => instance.style.wordWrap = b, updatesRendering = true)

  val lineHeight: Var[Double] = prop(0.0, (d: Double) => instance.style.lineHeight = d, updatesRendering = true)
  val wordWrapWidth: Var[Double] = prop(size.width(), (d: Double) => instance.style.wordWrapWidth = d, updatesRendering = true)

  dropShadow
  font
  value.on(measure())
  font.size.on(measure())

  // TODO: re-measure text on most changes

  protected def measure(): Unit = {
    val metrics = PIXI.TextMetrics.measureText(value(), instance.style, wordWrap = false)

    size.measured.width := metrics.width
    size.measured.height := metrics.height
  }
}

object BasicText extends BasicTextTheme