package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi._
import io.youi.drawable.Context
import io.youi.spatial.Size
import io.youi.theme.TextViewTheme
import org.scalajs.dom.html

class TextView(protected val element: html.Element) extends HTMLComponent[html.Element] with TextViewTheme {
  def this() = {
    this(dom.create[html.Span]("span"))
  }

  parentTheme := Some(TextView)

  override def componentType: String = "TextView"
}

object TextView extends TextViewTheme {
  private val measurer = new Context(dom.create[html.Canvas]("canvas"), 1.0)

  def measure(component: TextView): Size = {
    measurer.setFont(component.font.family().value, component.font.size, "", "", component.font.weight().value)
    measurer.measureText(component.value())
  }
}