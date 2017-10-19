package io.youi.util

import com.outr.pica.{Pica, ResizeOptions}
import io.youi.image.Image
import io.youi.video.Video
import io.youi.{dom, _}
import org.scalajs.dom._
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.{CanvasRenderingContext2D, File, FileReader, URL}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.|

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
    } else if (smooth && (srcWidth != destination.width || srcHeight != destination.height)) {
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

  def preview(file: File): Future[Option[Image]] = if (Video.isVideo(file)) {
    Video(file, autoPlay = false, loop = false, muted = true).flatMap { video =>
      video.seek(video.duration / 2.0).map { _ =>
        val image = video.createImage()
        video.dispose()
        Some(image)
      }
    }
  } else if (Image.isImage(file)) {
    Image(file).map(Some.apply)
  } else {
    Future.successful(None)
  }

  def preview(file: File, width: Double, height: Double, scaleUp: Boolean): Future[Option[Image]] = {
    preview(file).flatMap {
      case Some(image) => {
        val scaled = SizeUtility.scale(image.width, image.height, width, height, scaleUp)
        image.resize(scaled.width, scaled.height).map { resized =>
          image.dispose()
          Some(resized)
        }
      }
      case None => Future.successful(None)
    }
  }

  /**
    * Supports a Video or Image file and generates a smooth image preview for it.
    */
  def generatePreview(file: File, width: Double, height: Double, scaleUp: Boolean = false): Future[Option[String]] = {
    preview(file, width, height, scaleUp).flatMap {
      case Some(image) => {
        image.toDataURL.map(Some.apply)
      }
      case None => Future.successful(None)
    }
  }
}