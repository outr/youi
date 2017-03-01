package io.youi.hypertext

import reactify.Var
import io.youi.dom
import org.scalajs.dom.html.{Input => HTMLInput}

class TextInput extends Component {
  override protected[youi] val element: HTMLInput = dom.create[HTMLInput]("input")

  lazy val value: Var[String] = prop(element.value, element.value = _, mayCauseResize = false)

  init()
}