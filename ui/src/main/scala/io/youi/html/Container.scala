package io.youi.html

import io.youi.dom
import org.scalajs.dom.html.Div

class Container extends HTMLContainer with Component {
  override protected[youi] val element: Div = dom.create[Div]("div")
}
