package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi._
import io.youi.dom._
import io.youi.theme.{HTMLTextInputTheme, HTMLTextViewTheme, Theme}
import org.scalajs.dom._

class HTMLTextView(protected val element: html.Element) extends HTMLComponent[html.Element] with HTMLTextViewTheme {
  def this() = {
    this(dom.create[html.Span]("span"))
  }

  override protected def defaultParentTheme: Theme = HTMLTextView

  override def componentType: String = "HTMLTextView"
}

object HTMLTextView extends HTMLTextViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent
}