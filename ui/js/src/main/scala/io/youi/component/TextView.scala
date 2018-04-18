package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.dom
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

  lazy val value: Var[String] = connect[String](Var(""), element.textContent, element.textContent = _, "")

  connect[FontFamily](font.family, FontFamily(element.style.fontFamily), ff => element.style.fontFamily = ff.value, FontFamily.default)
  connect(font.size, element.style.fontSize match {
    case "auto" | "" => 0.0
    case s => throw new RuntimeException(s"Unsupported font size: [$s]")
  }, (v: Double) => element.style.fontSize = s"${v}px", 0.0)
}

object TextView extends TextViewTheme