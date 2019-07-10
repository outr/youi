package io.youi.font

import io.youi.component.extras.HTMLComponent
import io.youi.dom._
import io.youi.theme.{FontAwesomeViewTheme, Theme}
import org.scalajs.dom._

class FontAwesomeView(protected val element: html.Element,
                      val existing: Boolean = false) extends HTMLComponent[html.Element] with FontAwesomeViewTheme {
  def this() = {
    this(create[html.Element]("i"))
  }

  FontAwesome.load()

  override protected def defaultParentTheme: Theme = FontAwesomeView

  override def componentType: String = "FontAwesomeView"
}

object FontAwesomeView extends FontAwesomeViewTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): FontAwesomeViewTheme = {
    new FontAwesomeView(in.byId[html.Element](id), existing = true)
  }
}