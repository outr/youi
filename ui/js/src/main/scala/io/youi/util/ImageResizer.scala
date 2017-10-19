package io.youi.util

import com.outr.pica.{Pica, ResizeOptions}
import io.youi._
import org.scalajs.dom.html

import scala.concurrent.Future
import scala.scalajs.js.|

trait ImageResizer {
  final def resize(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    val src = source.asInstanceOf[html.Image]
    val srcWidth = src.width
    val srcHeight = src.height
    if (srcWidth <= 0 || srcHeight <= 0 || destination.width <= 0 || destination.height <= 0) {
      Future.successful(destination)
    } else if (srcWidth == destination.width && srcHeight == destination.height) {
      FastResizer.resizeInternal(source, destination)
    } else {
      resizeInternal(source, destination)
    }
  }
  protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas]
}

object ImageResizer {
  def Fast: ImageResizer = FastResizer
  def Pica: ImageResizer = PicaResizer
}

object PicaResizer extends ImageResizer {
  private lazy val pica: Pica = Pica()
  private val picaFuture = new SingleThreadedFuture()

  override protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    picaFuture {
      pica.resize(source, destination, new ResizeOptions {
        alpha = true
      }).toFuture
    }
  }
}

object FastResizer extends ImageResizer {
  override protected[util] def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Future[html.Canvas] = {
    destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
    Future.successful(destination)
  }
}