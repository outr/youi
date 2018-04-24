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

  lazy val value: Var[String] = connect[String](Var(""), element.textContent, element.textContent = _, "", updateSizeFromElement())

  connect[FontFamily](font.family, FontFamily(element.style.fontFamily), ff => element.style.fontFamily = ff.value, FontFamily.default, updateSizeFromElement())
  connect(font.size, element.style.fontSize match {
    case "auto" | "" => 0.0
    case s => throw new RuntimeException(s"Unsupported font size: [$s]")
  }, (v: Double) => element.style.fontSize = s"${v}px", 0.0, updateSizeFromElement())
  connect[Color](color, Color.Black, (c: Color) => element.style.color = c.toRGBA, Color.Black)

  override protected def determineActualWidth: Double = TextView.measure(this).width

  override protected def determineActualHeight: Double = TextView.measure(this).height
}

object TextView extends TextViewTheme {
  private val measurer = new Context(dom.create[html.Canvas]("canvas"), 1.0)

  def measure(component: TextView): Size = {
    measurer.setFont(component.font.family().value, component.font.size(), "", "", component.font.weight().value)
    measurer.measureText(component.value())
  }
}