package io.youi.hypertext

import reactify.Var
import io.youi.dom
import io.youi.hypertext.style.{FontProperties, TextOverflow}
import org.scalajs.dom.html.{Span => HTMLSpan}

class Label extends Component {
  override protected[youi] val element: HTMLSpan = dom.create[HTMLSpan]("span")

  val text: Var[String] = prop("", t => element.innerHTML = t, mayCauseResize = true)
  val preserveNewLines: Var[Boolean] = prop(false, _ => updateWhitespace(), mayCauseResize = true)
  val preserveWhitespace: Var[Boolean] = prop(false, _ => updateWhitespace(), mayCauseResize = true)
  val wrap: Var[Boolean] = prop(false, _ => updateWhitespace(), mayCauseResize = true)
  val textOverflow: Var[TextOverflow] = prop(TextOverflow.NoClip, to => element.style.textOverflow = to.value, mayCauseResize = true)
  val font: FontProperties = new FontProperties(this)

  init()

  override protected def init(): Unit = {
    super.init()

    updateWhitespace()
    element.style.textOverflow = textOverflow().value
  }

  private def updateWhitespace(): Unit = {
    val whiteSpace = (preserveNewLines(), preserveWhitespace(), wrap()) match {
      case (true, true, true) => "pre-wrap"
      case (false, false, false) => "nowrap"
      case (true, true, false) => "pre"
      case (true, false, true) => "pre-line"
      case (false, true, true) => "normal"      // Not supported
      case (true, false, false) => "pre"        // Not supported
      case (false, false, true) => "normal"
      case (false, true, false) => "nowrap"     // Not supported
    }
    element.style.whiteSpace = whiteSpace
  }
}