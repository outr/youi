package io.youi.image.resize

import org.scalajs.dom.html

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.|
import io.youi._
import io.youi.util.CanvasPool

object StepDownResizer extends ImageResizer {
  override protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    val src = source.asInstanceOf[html.Image]
    val srcWidth = src.width.toDouble
    val srcHeight = src.height.toDouble
    val destWidth = destination.width.toDouble
    val destHeight = destination.height.toDouble
    if (srcWidth / 2.0 >= destWidth && srcHeight / 2.0 >= destHeight) {
      // Scale by half
      val temp = CanvasPool(srcWidth / 2.0, srcHeight / 2.0)
      temp.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, temp.width, temp.height)
      val future = resizeInternal(temp, destination)
      future.onComplete(_ => CanvasPool.restore(temp))
      future
    } else {
      destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
      Future.successful(destination)
    }
  }
}