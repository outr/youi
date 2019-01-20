package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi._
import io.youi.dom._
import io.youi.theme.{HTMLTextViewTheme, Theme}
import org.scalajs.dom._
import org.scalajs.dom.html.Input

class HTMLTextView(protected val element: html.Element,
                   val existing: Boolean = false) extends HTMLComponent[html.Element] with HTMLTextViewTheme {
  def this() = {
    this(dom.create[html.Span]("span"))
  }

  override protected def defaultParentTheme: Theme = HTMLTextView

  override def componentType: String = "HTMLTextView"
}

object HTMLTextView extends HTMLTextViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): HTMLTextView = {
    new HTMLTextView(in.byId[Input](id), existing = true)
  }
}