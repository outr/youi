package io.youi.component

import com.outr.pixijs.PIXI
import reactify.{Observable, Var}

class Text extends Component {
  override protected[component] lazy val instance: PIXI.Text = new PIXI.Text("")
  private lazy val textStyle: PIXI.TextStyle = new PIXI.TextStyle

  lazy val value: Var[String] = Var.bound(instance.text, (s: String) => instance.text = s)

  Observable.wrap(value).on(measure())

  protected def measure(): Unit = {
    val metrics = PIXI.TextMetrics.measureText(value(), textStyle, wordWrap = false)

    size.measured.width := metrics.width
    size.measured.height := metrics.height
  }
}