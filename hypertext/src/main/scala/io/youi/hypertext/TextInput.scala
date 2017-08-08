package io.youi.hypertext

import io.youi.dom
import io.youi.hypertext.style.FontProperties
import org.scalajs.dom.html.{Input => HTMLInput}
import reactify.Var

class TextInput extends Component {
  override val element: HTMLInput = dom.create[HTMLInput]("input")

  lazy val value: Var[String] = prop(element.value, _ => changed(), mayCauseResize = false)
  val font: FontProperties = new FontProperties(this)

  init()

  event.change.attach { _ =>
    checkValue()
  }
  event.key.up.attach { _ =>
    checkValue()
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

  private def checkValue(): Unit = {
    val v = element.value
    if (value() != v && !changing) {
      changing = true
      try {
        value := v
      } finally {
        changing = false
      }
    }
  }
}