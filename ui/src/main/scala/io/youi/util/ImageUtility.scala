package io.youi.util

import org.scalajs.dom._

import scala.scalajs.js.|
import com.outr.pica.{Pica, ResizeOptions}
import io.youi._
import io.youi.dom
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{File, FileReader, URL}

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js

object ImageUtility {
  private lazy val pica: Pica = Pica()
  private val picaFuture = new SingleThreadedFuture()

  def resizeToCanvas(source: html.Image | html.Canvas,
                     destination: html.Canvas,
                     smooth: Boolean = false): Future[Canvas] = {
    val src = source.asInstanceOf[html.Image]
    val srcWidth = src.width
    val srcHeight = src.height
    if (srcWidth <= 0 || srcHeight <= 0 || destination.width <= 0 || destination.height <= 0) {
      Future.successful(destination)
    } else if (smooth && srcWidth != destination.width && srcHeight != destination.height) {
      picaFuture {
        pica.resize(source, destination, new ResizeOptions {
          alpha = true
        }).toFuture
      }
    } else {
      destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
      Future.successful(destination)
    }
  }

  def drawToCanvas(source: html.Image | html.Canvas,
                   destination: html.Canvas,
                   smooth: Boolean = true)
                  (offsetX: Double = 0.0,
                   offsetY: Double = 0.0,
                   width: Double = source.asInstanceOf[html.Image].width,
                   height: Double = source.asInstanceOf[html.Image].height): Future[html.Canvas] = {
    CanvasPool.withCanvasFuture(width, height) { canvas =>
      resizeToCanvas(source, canvas, smooth).map { _ =>
        destination.context.drawImage(canvas.asInstanceOf[html.Image], offsetX, offsetY, width, height)
        destination
      }
    }
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
    val future = drawToCanvas(source, destinationCanvas)(
      width = destinationCanvas.width,
      height = destinationCanvas.height
    ).map { _ =>
      destinationCanvas.toDataURL("image/png")
    }
    future.onComplete { _ =>
      CanvasPool.restore(destinationCanvas)
    }
    future
  }

  private val tempImage = dom.create[html.Image]("img")
  private val loadImageFuture = new SingleThreadedFuture()

  def loadImage[R](file: File)(process: html.Image => Future[R]): Future[R] = {
    loadImageFuture {
      val promise = Promise[Unit]
      val listener: js.Function1[Event, _] = (_: Event) => promise.success(())
      tempImage.addEventListener("load", listener)
      loadDataURL(file).foreach(tempImage.src = _)
      promise.future.flatMap { _ =>
        tempImage.removeEventListener("load", listener)
        val f = process(tempImage)
        f.onComplete { _ =>
          tempImage.src = ""
        }
        f
      }
    }
  }

  def loadDataURL(file: File, useFileReader: Boolean = false): Future[String] = if (useFileReader) {
    val reader = new FileReader
    val promise = Promise[String]
    reader.addEventListener("load", (evt: Event) => {
      promise.success(reader.result.asInstanceOf[String])
    })
    reader.readAsDataURL(file)
    val future = promise.future
    future.failed.foreach(scribe.error(_))
    future
  } else {
    Future.successful(URL.createObjectURL(file))
  }

  def loadText(file: File): Future[String] = {
    val reader = new FileReader
    val promise = Promise[String]
    reader.addEventListener("load", (_: Event) => {
      promise.success(reader.result.asInstanceOf[String])
    })
    reader.readAsText(file)
    val future = promise.future
    future.failed.foreach(scribe.error(_))
    future
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
        val scaled = SizeUtility.scale(video.videoWidth, video.videoHeight, width, height, scaleUp)
        resizeToDataURL(canvas, scaled.width, scaled.height).map { dataURL =>
          CanvasPool.restore(canvas)
          promise.success(dataURL)
        }
      }
      video.addEventListener("loadeddata", (evt: Event) => {
        createImage()
      })
      video.addEventListener("error", (evt: Event) => {
        promise.failure(new RuntimeException(s"Video $url failed to load!"))
      })
      video.addEventListener("seeked", (evt: Event) => {
        createImage()
      })
      video.src = url
    } else if (file.`type`.startsWith("image/")) {                                               // Image preview
      loadImage(file) { img =>
        val scaled = SizeUtility.scale(img.width, img.height, width, height, scaleUp)
        resizeToDataURL(img, scaled.width, scaled.height)
      }.foreach(promise.success)
    } else {
      // Unknown file type, no preview available
    }
    promise.future
  }
}