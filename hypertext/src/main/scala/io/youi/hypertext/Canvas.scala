package io.youi.hypertext

import io.youi._
import org.scalajs.dom._

class Canvas(val element: html.Canvas, manageSize: Boolean) extends Component {
  def this() = this(dom.create[html.Canvas]("canvas"), manageSize = true)
  def this(manageSize: Boolean) = this(dom.create[html.Canvas]("canvas"), manageSize)

  init()

  override protected def init(): Unit = {
    if (manageSize) {
      updateCanvasSize()
    }
    super.init()
  }

  protected def updateCanvasSize(): Unit = {
    size.actual.width.attach(w => element.width = math.ceil(w).toInt)
    size.actual.height.attach(h => element.height = math.ceil(h).toInt)
  }
}

object Canvas {
  def cached(element: html.Canvas, manageSize: Boolean = true): Canvas = {
    Component.cached[html.Canvas, Canvas](element, new Canvas(_, manageSize))
  }
}