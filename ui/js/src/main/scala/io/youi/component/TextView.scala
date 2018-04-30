package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.{Color, dom}
import io.youi.drawable.Context
import io.youi.spatial.Size
import io.youi.style.FontFamily
import io.youi.theme.TextViewTheme
import org.scalajs.dom.html
import reactify.Var

class TextView(protected val element: html.Element) extends HTMLComponent[html.Element] with TextViewTheme {
  def this() = {
    this(dom.create[html.Span]("span"))
  }

  override lazy val theme: Var[TextViewTheme] = Var(TextView)
  override def `type`: String = "TextView"

  lazy val value: Var[String] = connect[String](Var(""), None, element.textContent = _)

  connect[FontFamily](font.family, None, ff => element.style.fontFamily = ff.value)
  connect(font.size, None, (v: Double) => element.style.fontSize = s"${v}px")
  connect[Color](color, None, (c: Color) => element.style.color = c.toRGBA)

  override protected def measuredWidth: Double = TextView.measure(this).width

  override protected def measuredHeight: Double = TextView.measure(this).height
}

object TextView extends TextViewTheme {
  private val measurer = new Context(dom.create[html.Canvas]("canvas"), 1.0)

  def measure(component: TextView): Size = {
    measurer.setFont(component.font.family().value, component.font.size(), "", "", component.font.weight().value)
    measurer.measureText(component.value())
  }
}