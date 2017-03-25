package io.youi.hypertext

import org.scalajs.dom._
import io.youi._

class Canvas extends Component {
  override protected[youi] val element: html.Canvas = dom.create[html.Canvas]("canvas")

  init()
}
