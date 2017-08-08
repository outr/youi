package io.youi.hypertext

import org.scalajs.dom.html

class BasicComponent(val element: html.Element) extends Component {
  init()
}

object BasicComponent {
  def cached(element: html.Element): BasicComponent = {
    Component.cached[html.Element, BasicComponent](element, new BasicComponent(_))
  }
}