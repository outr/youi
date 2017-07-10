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

  def resizeToCanvas(source: html.Image | html.Canvas, destination: html.Canvas): Future[Canvas] = {
    (source: Any) match {
      case i: html.Image => assert(i.width > 0 && i.height > 0, "Cannot resizeToCanvas for zero size image as source!")
      case c: html.Canvas => assert(c.width > 0 && c.height > 0, "Cannot resizeToCanvas for zero size canvas as source!")
    }

    picaFuture {
      pica.resize(source, destination, new ResizeOptions {
        alpha = true
      }).toFuture
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
    resizeToCanvas(source, destinationCanvas).map { _ =>
      try {
        destinationCanvas.toDataURL("image/png")
      } finally {
        CanvasPool.restore(destinationCanvas)
      }
    }
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

  def loadDataURL(file: File): Future[String] = {
    val reader = new FileReader
    val promise = Promise[String]
    reader.addEventListener("load", (evt: Event) => {
      promise.success(reader.result.asInstanceOf[String])
    })
    reader.readAsDataURL(file)
    val future = promise.future
    future.failed.foreach(scribe.error(_))
    future
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
      video.addEventListener("seeked", (evt: Event) => {
        createImage()
      })
      video.src = url
    } else if (file.`type`.startsWith("image/")) {                                               // Image preview
      loadImage(file) { img =>
        val scaled = SizeUtility.scale(img.width, img.height, width, height, scaleUp)
        resizeToDataURL(img, scaled.width, scaled.height)
      }
    } else {
      // Unknown file type, no preview available
    }
    val future = promise.future
    future.failed.foreach(scribe.error(_))
    future
  }
}