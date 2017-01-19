package io.youi.html
import com.outr.reactify.Var
import io.youi.dom
import org.scalajs.dom.html.{Span => HTMLSpan}

class Label extends Component {
  override protected[youi] val element: HTMLSpan = dom.create[HTMLSpan]("span")

  val text: Var[String] = prop("", t => element.innerHTML = t, mayCauseResize = true)

  init()
}