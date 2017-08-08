package io.youi.hypertext

import io.youi._
import org.scalajs.dom._

class Canvas(val element: html.Canvas) extends Component {
  def this() = this(dom.create[html.Canvas]("canvas"))

  init()

  override protected def init(): Unit = {
    size.actual.width.attach(w => element.width = math.ceil(w).toInt)
    size.actual.height.attach(h => element.height = math.ceil(h).toInt)
    super.init()
  }
}

object Canvas {
  def cached(element: html.Canvas): Canvas = Component.cached[html.Canvas, Canvas](element, new Canvas(_))
}