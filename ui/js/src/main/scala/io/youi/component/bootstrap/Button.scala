package io.youi.component.bootstrap

import io.youi.style.FontFamily
import io.youi.{Color, dom}
import io.youi.theme.bootstrap.ButtonTheme
import io.youi.util.Measurer
import org.scalajs.dom._
import reactify.Var

class Button(override val element: html.Button) extends BootstrapComponent[html.Button] with ButtonTheme {
  def this() = {
    this(dom.create[html.Button]("button"))
  }

  override lazy val theme: Var[ButtonTheme] = Var(Button)
  override def `type`: String = "bootstrap.Button"

  element.classList.add("btn")
  element.classList.add("btn-primary")

  // TODO: extract into mix-in
  connect[FontFamily](font.family, None, ff => element.style.fontFamily = ff.value, resetMeasured())
  connect(font.size, None, (v: Double) => element.style.fontSize = s"${v}px", resetMeasured())
  connect[Color](color, None, (c: Color) => element.style.color = c.toRGBA, resetMeasured())

  lazy val value: Var[String] = connect[String](Var(""), None, element.textContent = _, resetMeasured())

  // TODO: make this the default for HTMLComponent and make `measured` a `Val`
  override protected def measuredWidth: Double = Measurer.measure(element).width
  override protected def measuredHeight: Double = Measurer.measure(element).height
}

object Button extends ButtonTheme