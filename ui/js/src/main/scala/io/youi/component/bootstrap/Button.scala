package io.youi.component.bootstrap

import io.youi.component.extras.HTMLComponent
import io.youi.dom._
import io.youi.theme.{StyleConnect, Theme}
import io.youi.theme.bootstrap.ButtonTheme
import org.scalajs.dom._
import reactify.Var

class Button(override val element: html.Button) extends BootstrapComponent[html.Button] with ButtonTheme {
  def this() = {
    this(create[html.Button]("button"))
  }

  override protected def defaultParentTheme: Theme = Button

  override def componentType: String = "bootstrap.Button"

  element.classList.add("btn")

  lazy val value: Var[String] = style[String]("value", "", StyleConnect.content[String], updatesTransform = true)
}

object Button extends ButtonTheme {
  override protected def defaultParentTheme: Theme = HTMLComponent

  def existing(id: String, in: html.Element = document.body): Button = {
    val e = in.byId[html.Button](id)
    val b = new Button(e)
    b
  }
}