package io.youi.hypertext

import reactify.Var
import io.youi.dom
import org.scalajs.dom.html.{Button => HTMLButton}

class Button extends Component {
  override protected[youi] val element: HTMLButton = dom.create[HTMLButton]("button")

  lazy val text: Var[String] = prop(element.textContent, value => element.textContent = value, mayCauseResize = true)

  init()
}