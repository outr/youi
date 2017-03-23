package io.youi.hypertext

import reactify.Var
import io.youi.dom
import io.youi.hypertext.style.FontProperties
import org.scalajs.dom.html.{Button => HTMLButton}

class Button extends Component {
  override protected[youi] val element: HTMLButton = dom.create[HTMLButton]("button")

  lazy val text: Var[String] = prop(element.textContent, value => element.textContent = value, mayCauseResize = true)
  val font: FontProperties = new FontProperties(this)

  init()
}