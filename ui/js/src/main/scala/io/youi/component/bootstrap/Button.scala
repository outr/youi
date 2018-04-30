package io.youi.component.bootstrap

import io.youi.dom
import io.youi.theme.ComponentTheme
import org.scalajs.dom._
import reactify.Var

class Button(override val element: html.Button) extends BootstrapComponent[html.Button] {
  def this() = {
    this(dom.create[html.Button]("button"))
  }

  override lazy val theme: Var[ComponentTheme] = Var(Button)
  override def `type`: String = "bootstrap.Button"

  element.classList.add("btn")
  element.classList.add("btn-primary")

  lazy val value: Var[String] = connect[String](Var(""), None, element.textContent = _)
}

object Button extends ComponentTheme