package io.youi.component

import com.outr.pixijs.PIXI
import io.youi._
import io.youi.style.{Fill, Theme}
import reactify.Var

class Text extends Component {
  override protected[component] lazy val instance: PIXI.Text = new PIXI.Text("")
  override protected def defaultTheme: Theme = Text

  lazy val value: Var[String] = Var.bound(instance.text, (s: String) => instance.text = s)

  val breakWords: Var[Boolean] = Var.bound(theme.breakWords, (b: Boolean) => instance.style.breakWords = b, setImmediately = true)
  object dropShadow {
    val enabled: Var[Boolean] = Var.bound(theme.dropShadow.enabled, (b: Boolean) => instance.style.dropShadow = b, setImmediately = true)
    val angle: Var[Double] = Var.bound(theme.dropShadow.angle, (d: Double) => instance.style.dropShadowAngle = d, setImmediately = true)
    val blur: Var[Double] = Var.bound(theme.dropShadow.blur, (d: Double) => instance.style.dropShadowBlur = d, setImmediately = true)
    val color: Var[Color] = Var.bound(theme.dropShadow.color, (c: Color) => instance.style.dropShadowColor = c.hex, setImmediately = true)
    val distance: Var[Double] = Var.bound(theme.dropShadow.distance, (d: Double) => instance.style.dropShadowDistance = d, setImmediately = true)
  }
  val fill: Var[Fill] = Var.bound(theme.fill, (f: Fill) => f.fill(instance.style), setImmediately = true)
  object font {
    val family: Var[String] = Var.bound(theme.font.family, (s: String) => instance.style.fontFamily = s, setImmediately = true)
    val size: Var[Double] = Var.bound(theme.font.size, (d: Double) => instance.style.fontSize = d, setImmediately = true)
    val style: Var[String] = Var.bound(theme.font.style, (s: String) => instance.style.fontStyle = s, setImmediately = true)
    val variant: Var[String] = Var.bound(theme.font.variant, (s: String) => instance.style.fontVariant = s, setImmediately = true)
    val weight: Var[String] = Var.bound(theme.font.weight, (s: String) => instance.style.fontWeight = s, setImmediately = true)
  }
  val letterSpacing: Var[Double] = Var.bound(theme.letterSpacing, (d: Double) => instance.style.letterSpacing = d, setImmediately = true)
  val lineJoin: Var[String] = Var.bound(theme.lineJoin, (s: String) => instance.style.lineJoin = s, setImmediately = true)
  val miterLimit: Var[Double] = Var.bound(theme.miterLimit, (d: Double) => instance.style.miterLimit = d, setImmediately = true)
  val padding: Var[Double] = Var.bound(theme.padding, (d: Double) => instance.style.padding = d, setImmediately = true)
  val stroke: Var[Color] = Var.bound(theme.stroke, (c: Color) => instance.style.stroke = c.hex, setImmediately = true)
  val strokeThickness: Var[Double] = Var.bound(theme.strokeThickness, (d: Double) => instance.style.strokeThickness = d, setImmediately = true)
  val textBaseline: Var[String] = Var.bound(theme.textBaseline, (s: String) => instance.style.textBaseline = s, setImmediately = true)
  val wordWrap: Var[Boolean] = Var.bound(theme.wordWrap, (b: Boolean) => instance.style.wordWrap = b, setImmediately = true)

  val lineHeight: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.lineHeight = d)
  val wordWrapWidth: Var[Double] = Var.bound(size.width(), (d: Double) => instance.style.wordWrapWidth = d)

  dropShadow
  font
  value.on(measure())
  font.size.on(measure())

  protected def measure(): Unit = {
    val metrics = PIXI.TextMetrics.measureText(value(), instance.style, wordWrap = false)

    size.measured.width := metrics.width
    size.measured.height := metrics.height
  }
}

object Text extends Theme(Theme)