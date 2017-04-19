package io.youi.component

import com.outr.pixijs.PIXI
import io.youi._
import io.youi.style.Fill
import reactify.Var

class Text extends Component {
  override protected[component] lazy val instance: PIXI.Text = new PIXI.Text("")

  lazy val value: Var[String] = Var.bound(instance.text, (s: String) => instance.text = s)

  lazy val breakWords: Var[Boolean] = Var.bound(false, (b: Boolean) => instance.style.breakWords = b, setImmediately = true)
  object dropShadow {
    lazy val enabled: Var[Boolean] = Var.bound(false, (b: Boolean) => instance.style.dropShadow = b, setImmediately = true)
    lazy val angle: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.dropShadowAngle = d, setImmediately = true)
    lazy val blur: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.dropShadowBlur = d, setImmediately = true)
    lazy val color: Var[Color] = Var.bound(Color.Black, (c: Color) => instance.style.dropShadowColor = c.hex, setImmediately = true)
    lazy val distance: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.dropShadowDistance = d, setImmediately = true)
  }
  lazy val fill: Var[Fill] = Var.bound(Color.Black, (f: Fill) => f.fill(instance.style), setImmediately = true)
  object font {
    lazy val family: Var[String] = Var.bound("", (s: String) => instance.style.fontFamily = s)
    lazy val size: Var[Double] = Var.bound(12.0, (d: Double) => instance.style.fontSize = d, setImmediately = true)
    lazy val style: Var[String] = Var.bound("", (s: String) => instance.style.fontStyle = s)
    lazy val variant: Var[String] = Var.bound("", (s: String) => instance.style.fontVariant = s)
    lazy val weight: Var[String] = Var.bound("", (s: String) => instance.style.fontWeight = s)
  }
  lazy val letterSpacing: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.letterSpacing = d)
  lazy val lineHeight: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.lineHeight = d)
  lazy val lineJoin: Var[String] = Var.bound("", (s: String) => instance.style.lineJoin = s)
  lazy val miterLimit: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.miterLimit = d)
  lazy val padding: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.padding = d)
  lazy val stroke: Var[Color] = Var.bound(Color.Clear, (c: Color) => instance.style.stroke = c.hex)
  lazy val strokeThickness: Var[Double] = Var.bound(0.0, (d: Double) => instance.style.strokeThickness = d)
  lazy val textBaseline: Var[String] = Var.bound("", (s: String) => instance.style.textBaseline = s)
  val wordWrap: Var[Boolean] = Var.bound(false, (b: Boolean) => instance.style.wordWrap = b, setImmediately = true)
  val wordWrapWidth: Var[Double] = Var.bound(size.width(), (d: Double) => instance.style.wordWrapWidth = d)

  value.on(measure())
  font.size.on(measure())

  protected def measure(): Unit = {
    val metrics = PIXI.TextMetrics.measureText(value(), instance.style, wordWrap = false)

    size.measured.width := metrics.width
    size.measured.height := metrics.height
  }
}