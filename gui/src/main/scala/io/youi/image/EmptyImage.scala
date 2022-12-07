package io.youi.image

import cats.effect.IO
import io.youi.drawable.Context
import io.youi.image.resize.ImageResizer
import org.scalajs.dom.html

object EmptyImage extends Image {
  override val width: Double = 0.0
  override val height: Double = 0.0

  override def draw(context: Context, x: Double, y: Double, width: Double, height: Double): Unit = ()

  override def dispose(): Unit = {}

  override def isVector: Boolean = true

  override def toDataURL: IO[String] = throw new RuntimeException("Empty image cannot be represented as a data url.")

  override def resize(width: Double, height: Double): IO[Image] = IO.pure(this)

  override def resizeTo(canvas: html.Canvas, width: Double, height: Double, resizer: ImageResizer): IO[html.Canvas] = IO.pure(canvas)

  override def toString: String = "EmptyImage"
}