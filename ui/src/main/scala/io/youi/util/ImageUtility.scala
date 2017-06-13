package io.youi.util

import org.scalajs.dom.html

import scala.scalajs.js.|
import com.outr.pica.{Pica, ResizeOptions}
import org.scalajs.dom.html.Canvas

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ImageUtility {
  private lazy val pica: Pica = Pica()

  def resizeToCanvas(source: html.Image | html.Canvas, destination: html.Canvas): Future[Canvas] = {
    pica.resize(source, destination, new ResizeOptions {
      alpha = true
    }).toFuture
  }

  def resizeToImage(source: html.Image | html.Canvas,
                    width: Double,
                    height: Double,
                    destination: html.Image): Future[html.Image] = {
    scribe.info(s"Source: $source, Destination: $destination")
    val destinationCanvas = CanvasPool(width, height)
    resizeToCanvas(source, destinationCanvas).map { _ =>
      val dataURL = destinationCanvas.toDataURL("image/png")
      destination.src = dataURL
      CanvasPool.restore(destinationCanvas)

      destination
    }
  }
}