package io.youi.util

import org.scalajs.dom._

import scala.scalajs.js.|
import com.outr.pica.{Pica, ResizeOptions}
import io.youi._
import io.youi.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{File, FileReader, UIEvent, URL}

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object ImageUtility {
  private lazy val pica: Pica = Pica()

  private var single: Future[_] = Future.successful(())

  def resizeToCanvas(source: html.Image | html.Canvas, destination: html.Canvas): Future[Canvas] = {
    val promise = Promise[Canvas]
    single.onComplete { _ =>
      val future = pica.resize(source, destination, new ResizeOptions {
        alpha = true
      }).toFuture
      future.onComplete {
        case Success(canvas) => promise.success(canvas)
        case Failure(t) => {
          scribe.error(t)
          promise.failure(t)
        }
      }
    }
    val f = promise.future
    single = f
    f
  }

  def resizeToImage(source: html.Image | html.Canvas,
                    width: Double,
                    height: Double,
                    destination: html.Image): Future[html.Image] = {
    resizeToDataURL(source, width, height).map { dataURL =>
      destination.src = dataURL
      destination
    }
  }

  def resizeToDataURL(source: html.Image | html.Canvas, width: Double, height: Double): Future[String] = {
    val destinationCanvas = CanvasPool(width, height)
    resizeToCanvas(source, destinationCanvas).map { _ =>
      try {
        destinationCanvas.toDataURL("image/png")
      } finally {
        CanvasPool.restore(destinationCanvas)
      }
    }
  }

  def loadImages(input: html.Input): Future[List[html.Image]] = {
    val files = (0 until input.files.length).map(index => input.files(index)).toList
    Future.sequence(files.map(loadImage))
  }

  def loadImage(file: File): Future[html.Image] = {
    val img = dom.create[html.Image]("img")
    val promise = Promise[html.Image]

    img.addEventListener("load", (evt: Event) => {
      promise.success(img)
    })
    loadDataURL(file).foreach(img.src = _)
    promise.future
  }

  def loadDataURL(file: File): Future[String] = {
    val reader = new FileReader
    val promise = Promise[String]
    reader.addEventListener("load", (evt: Event) => {
      promise.success(reader.result.asInstanceOf[String])
    })
    reader.readAsDataURL(file)
    promise.future
  }

  /**
    * Supports a Video or Image file and generates a smooth image preview for it.
    */
  def generatePreview(file: File,
                      width: Double,
                      height: Double,
                      scaleUp: Boolean = false): Future[String] = {
    val promise = Promise[String]
    if (file.`type`.startsWith("video/")) {                                                       // Video preview
      val video = document.createElement("video").asInstanceOf[html.Video]

      val url = URL.createObjectURL(file)
      video.autoplay = false
      video.preload = "auto"
      video.addEventListener("loadedmetadata", (evt: Event) => {
        video.currentTime = video.duration / 2.0
      })
      def createImage(): Unit = {
        val canvas = CanvasPool(video.videoWidth, video.videoHeight)
        val context = canvas.context
        context.drawImage(video, 0.0, 0.0)
        val size = SizeUtility.scale(video.videoWidth, video.videoHeight, width, height, scaleUp)
        resizeToDataURL(canvas, size.x, size.y).map { dataURL =>
          CanvasPool.restore(canvas)
          promise.success(dataURL)
        }
      }
      video.addEventListener("loadeddata", (evt: Event) => {
        createImage()
      })
      video.addEventListener("seeked", (evt: Event) => {
        createImage()
      })
      video.src = url
    } else if (file.`type`.startsWith("image/")) {                                               // Image preview
      loadImage(file).flatMap { img =>
        val size = SizeUtility.scale(img.width, img.height, width, height, scaleUp)
        resizeToDataURL(img, size.x, size.y)
      }
    } else {
      // Unknown file type, no preview available
    }
    promise.future
  }
}