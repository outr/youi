package youi.image

import rapid.Task
import youi.drawable.Context
import youi.image.resize.ImageResizer
import org.scalajs.dom.html

object EmptyImage extends Image {
  override val width: Double = 0.0
  override val height: Double = 0.0

  override def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = ()

  override def dispose(): Unit = {}

  override def isVector: Boolean = true

  override def toDataURL: Task[String] = throw new RuntimeException("Empty image cannot be represented as a data url.")

  override def resize(width: Double, height: Double): Task[Image] = Task.pure(this)

  override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): Task[html.Canvas] = Task.pure(canvas)

  override def toString: String = "EmptyImage"
}
