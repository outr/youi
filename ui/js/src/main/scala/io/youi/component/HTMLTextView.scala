package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi._
import io.youi.drawable.Context
import io.youi.spatial.Size
import io.youi.theme.{HTMLTextViewTheme, TextViewTheme, Theme}
import org.scalajs.dom.html

class HTMLTextView(protected val element: html.Element) extends HTMLComponent[html.Element] with HTMLTextViewTheme {
  def this() = {
    this(dom.create[html.Span]("span"))
  }

  override protected def defaultParentTheme: Theme = HTMLTextView

  override def componentType: String = "HTMLTextView"
}

object HTMLTextView extends HTMLTextViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  private val measurer = new Context(dom.create[html.Canvas]("canvas"), 1.0)

  def measure(component: HTMLTextView): Size = {
    measurer.setFont(component.font.family().value, component.font.size, "", "", component.font.weight().value)
    measurer.measureText(component.value())
  }
}