package io.youi.component

import com.outr.pixijs.PIXI
import io.youi._
import io.youi.style.Fill
import reactify.Var

class Text extends Component {
  override protected[component] lazy val instance: PIXI.Text = new PIXI.Text("")

  lazy val value: Var[String] = Var.bound(instance.text, (s: String) => instance.text = s)
  object font {
    lazy val size: Var[Double] = Var.bound(12.0, (d: Double) => instance.style.fontSize = d, setImmediately = true)
  }
  lazy val fill: Var[Fill] = Var.bound(Color.Black, (f: Fill) => f.fill(instance.style), setImmediately = true)

  value.on(measure())
  font.size.on(measure())

  protected def measure(): Unit = {
    val metrics = PIXI.TextMetrics.measureText(value(), instance.style, wordWrap = false)

    size.measured.width := metrics.width
    size.measured.height := metrics.height
  }
}