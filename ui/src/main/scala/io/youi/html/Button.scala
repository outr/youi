package io.youi.html

import com.outr.props.{Channel, Var}
import io.youi.dom
import org.scalajs.dom.html.Element
import org.scalajs.dom.html.{Button => HTMLButton}

class Button extends Component {
  override protected[youi] val element: HTMLButton = dom.create[HTMLButton]("button")

  lazy val text: Var[String] = prop(element.textContent, value => element.textContent = value)

  lazy val action: Channel[Unit] = Channel[Unit]
}