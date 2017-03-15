package io.youi.hypertext

import reactify.Var
import io.youi.dom
import io.youi.hypertext.style.FontProperties
import org.scalajs.dom.html.{Input => HTMLInput}

class TextInput extends Component {
  override protected[youi] val element: HTMLInput = dom.create[HTMLInput]("input")

  lazy val value: Var[String] = prop(element.value, _ => changed(), mayCauseResize = false)
  val font: FontProperties = new FontProperties(this)

  init()

  event.change.attach { _ =>
    changing = true
    try {
      value := element.value
    } finally {
      changing = false
    }
  }

  private var changing = false
  private def changed(): Unit = if (!changing) {
    changing = true
    try {
      element.value = value()
    } finally {
      changing = false
    }
  }
}