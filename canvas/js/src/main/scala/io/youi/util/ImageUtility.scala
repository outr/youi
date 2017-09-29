package io.youi.util

import org.scalajs.dom._

import scala.scalajs.js.|
import com.outr.pica.{Pica, ResizeOptions}
import io.youi._
import io.youi.dom
import io.youi.image._
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{CanvasRenderingContext2D, File, FileReader, URL}

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
      destination.getContext("2d").asInstanceOf[CanvasRenderingContext2D].drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
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
        destination.getContext("2d").asInstanceOf[CanvasRenderingContext2D].drawImage(canvas.asInstanceOf[html.Image], offsetX, offsetY, width, height)
        destination
      }
    }
  }

  def resizeToImage(source: html.Image | html.Canvas,
                    width: Double,
                    height: Double,
                    destination: html.Image,
                    smooth: Boolean): Future[html.Image] = {
    resizeToDataURL(source, width, height, smooth).map { dataURL =>
      destination.src = dataURL
      destination
    }
  }

  def resizeToDataURL(source: html.Image | html.Canvas, width: Double, height: Double, smooth: Boolean): Future[String] = {
    val destinationCanvas = CanvasPool(width, height)
    val loader = if (source.asInstanceOf[html.Image].width == 0 || source.asInstanceOf[html.Image].height == 0) {
      loadImage(source.asInstanceOf[html.Image])
    } else {
      Future.successful(source.asInstanceOf[html.Image])
    }
    val future = loader.flatMap { _ =>
      drawToCanvas(source, destinationCanvas, smooth)(
        width = destinationCanvas.width,
        height = destinationCanvas.height
      ).map { _ =>
        destinationCanvas.toDataURL("image/png")
      }
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

  def loadImage(img: html.Image): Future[html.Image] = if (img.width > 0 && img.height > 0) {
    Future.successful(img)
  } else {
    val promise = Promise[html.Image]
    val listener: js.Function1[Event, _] = (_: Event) => {
      promise.success(img)
    }
    img.addEventListener("load", listener)
    val future = promise.future
    future.onComplete { _ =>
      img.removeEventListener("load", listener)
    }
    future
  }

  def toDataURL(img: html.Image): Future[String] = loadImage(img).map { _ =>
    CanvasPool.withCanvas(img.width, img.height) { canvas =>
      val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
      ctx.drawImage(img, 0.0, 0.0)
      canvas.toDataURL("image/png")
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
                      smooth: Boolean,
                      scaleUp: Boolean = false): Future[Option[String]] = {
    val promise = Promise[Option[String]]
    if (file.`type`.startsWith("video/")) { // Video preview
      val video = document.createElement("video").asInstanceOf[html.Video]

      val url = URL.createObjectURL(file)
      video.autoplay = false
      video.preload = "auto"
      video.addEventListener("loadedmetadata", (evt: Event) => {
        video.currentTime = video.duration / 2.0
      })

      def createImage(): Unit = {
        val canvas = CanvasPool(video.videoWidth, video.videoHeight)
        val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
        context.drawImage(video, 0.0, 0.0)
        val scaled = SizeUtility.scale(video.videoWidth, video.videoHeight, width, height, scaleUp)
        resizeToDataURL(canvas, scaled.width, scaled.height, smooth).map { dataURL =>
          CanvasPool.restore(canvas)
          promise.success(Some(dataURL))
        }
      }

      video.addEventListener("loadeddata", (evt: Event) => {
        createImage()
      })
      video.addEventListener("error", (evt: Event) => {
        promise.success(None)
      })
      video.addEventListener("seeked", (evt: Event) => {
        createImage()
      })
      video.src = url
    } else if (file.`type` == "image/svg+xml") {                                                 // SVG preview
      ImageUtility.loadText(file).flatMap { svgString =>
        Image.fromSVGString(svgString, None, None).flatMap { image =>
          val scaled = SizeUtility.scale(image.width, image.height, width, height, scaleUp)
          val drawable = new ComponentDrawer
          drawable.updateAsync(scaled.width, scaled.height)(context => image.draw(context, scaled.width, scaled.height)).map { _ =>
            try {
              val dataURL = drawable.context.canvas.toDataURL("image/png")
              promise.success(Some(dataURL))
            } finally {
              drawable.dispose()
            }
          }
        }
      }
    } else if (file.`type`.startsWith("image/")) {                                               // Image preview
      loadImage(file) { img =>
        val scaled = SizeUtility.scale(img.width, img.height, width, height, scaleUp)
        resizeToDataURL(img, scaled.width, scaled.height, smooth)
      }.foreach(dataURL => promise.success(Option(dataURL)))
    } else {
      // Unknown file type, no preview available
      scribe.warn(s"Unknown type (${file.`type`}) for ${file.name}.")
      promise.success(None)
    }
    promise.future
  }
}