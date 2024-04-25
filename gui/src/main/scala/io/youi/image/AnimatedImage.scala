package io.youi.image

import cats.effect.IO
import cats.implicits.toTraverseOps
import io.youi.drawable.Context
import io.youi.image.resize.ImageResizer
import org.scalajs.dom.html.Canvas
import reactify._

class AnimatedImage(frames: Vector[Image], delay: Double) extends Image {
  assert(frames.nonEmpty, "AnimatedImage.frames cannot be empty")

  val index: Var[Int] = Var(0)
  val frame: Val[Image] = Val(frames(index))
  override val width: Double = frames.head.width
  override val height: Double = frames.head.height

  private var lastFrame: Long = System.currentTimeMillis()

  override def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = {
    val time = System.currentTimeMillis()
    val delta = (time - lastFrame) / 1000.0
    if (delta >= delay) {
      if (frames.length > index + 1) {
        index.static(index + 1)
      } else {
        index @= 0
      }
      lastFrame = time
    }
    frame.draw(context, x, y, width, height)

    modified @= time
  }

  override def resize(width: Double, height: Double): IO[Image] = {
    frames.map(_.resize(width, height)).sequence.map(new AnimatedImage(_, delay))
  }

  override def resizeTo(canvas: Canvas, width: Double, height: Double, resizer: ImageResizer): IO[Canvas] = {
    frame.resizeTo(canvas, width, height, resizer)
  }

  override def isVector: Boolean = frame.isVector

  override def toDataURL: IO[String] = frame.toDataURL

  override def dispose(): Unit = frames.foreach(_.dispose())
}