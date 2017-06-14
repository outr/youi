package io.youi.util

import org.scalajs.dom.{Event, html}

import scala.scalajs.js.|
import com.outr.pica.{Pica, ResizeOptions}
import io.youi.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{File, FileReader}

import scala.concurrent.{Future, Promise}
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
    val destinationCanvas = CanvasPool(width, height)
    resizeToCanvas(source, destinationCanvas).map { _ =>
      val dataURL = destinationCanvas.toDataURL("image/png")
      destination.src = dataURL
      CanvasPool.restore(destinationCanvas)

      destination
    }
  }

  def loadImages(input: html.Input): Future[List[html.Image]] = {
    val files = (0 until input.files.length).map(index => input.files(index)).toList
    Future.sequence(files.map(loadImage))
  }

  def loadImage(file: File): Future[html.Image] = {
    val img = dom.create[html.Image]("img")
    val reader = new FileReader
    val promise = Promise[html.Image]
    reader.addEventListener("load", (evt: Event) => {
      img.src = reader.result.asInstanceOf[String]
    })
    img.addEventListener("load", (evt: Event) => {
      promise.success(img)
    })
    reader.readAsDataURL(file)
    promise.future
  }
}