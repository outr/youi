package io.youi.image.resize

import cats.effect.IO
import io.youi._
import io.youi.util.CanvasPool
import org.scalajs.dom.html

import scala.scalajs.js.|

object StepDownResizer extends ImageResizer {
  override protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): IO[html.Canvas] = {
    val src = source.asInstanceOf[html.Image]
    val srcWidth = src.width.toDouble
    val srcHeight = src.height.toDouble
    val destWidth = destination.width.toDouble
    val destHeight = destination.height.toDouble
    if (srcWidth / 2.0 >= destWidth && srcHeight / 2.0 >= destHeight) {
      // Scale by half
      val temp = CanvasPool(srcWidth / 2.0, srcHeight / 2.0)
      temp.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, temp.width, temp.height)
      resizeInternal(temp, destination).map { canvas =>
        CanvasPool.restore(temp)
        canvas
      }
    } else {
      destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
      IO.pure(destination)
    }
  }
}