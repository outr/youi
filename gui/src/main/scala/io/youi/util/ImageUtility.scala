package io.youi.util

import rapid.Task
import rapid.task.Completable
import io.youi.image.Image
import io.youi.image.resize.ImageResizer
import io.youi.video.Video
import io.youi.{dom, _}
import org.scalajs.dom._

import scala.scalajs.js
import scala.scalajs.js.|

object ImageUtility {
  def drawToCanvas(source: html.Image | html.Canvas,
                   destination: html.Canvas,
                   resizer: ImageResizer)
                  (offsetX: Double = 0.0,
                   offsetY: Double = 0.0,
                   width: Double = source.asInstanceOf[html.Image].width,
                   height: Double = source.asInstanceOf[html.Image].height): Task[html.Canvas] = {
    CanvasPool.withCanvasTask(width, height) { canvas =>
      resizer.resize(source, canvas).map { _ =>
        destination.context.drawImage(canvas, offsetX, offsetY, width, height)
        destination
      }
    }
  }

  def resizeToImage(source: html.Image | html.Canvas,
                    width: Double,
                    height: Double,
                    destination: html.Image,
                    resizer: ImageResizer): Task[html.Image] = {
    resizeToDataURL(source, width, height, resizer).map { dataURL =>
      destination.src = dataURL
      destination
    }
  }

  def resizeToDataURL(source: html.Image | html.Canvas, width: Double, height: Double, resizer: ImageResizer): Task[String] = {
    val destinationCanvas = CanvasPool(width, height)
    val loader = if (source.asInstanceOf[html.Image].width == 0 || source.asInstanceOf[html.Image].height == 0) {
      loadImage(source.asInstanceOf[html.Image])
    } else {
      Task.pure(source.asInstanceOf[html.Image])
    }
    loader.flatMap { _ =>
      drawToCanvas(source, destinationCanvas, resizer)(
        width = destinationCanvas.width,
        height = destinationCanvas.height
      ).map { _ =>
        destinationCanvas.toDataURL("image/png")
      }
    }.guarantee(Task {
      CanvasPool.restore(destinationCanvas)
    })
  }

  private val tempImage = dom.create[html.Image]("img")

  def loadImage[R](file: File)(process: html.Image => Task[R]): Task[R] = {
    val c: Completable[Unit] = Task.completable[Unit]
    var listener: js.Function1[Event, ?] = null
    listener = (_: Event) => {
      tempImage.removeEventListener("load", listener)
      c.success(())
    }
    tempImage.addEventListener("load", listener)
    FileUtility.loadDataURL(file).map(tempImage.src = _).startUnit()
    c.flatMap { _ =>
      process(tempImage).guarantee(Task {
        tempImage.src = ""
      })
    }
  }

  def loadImage(img: html.Image): Task[html.Image] = if (img.width > 0 && img.height > 0) {
    Task.pure(img)
  } else {
    val c: Completable[html.Image] = Task.completable[html.Image]
    var listener: js.Function1[Event, ?] = null
    listener = (_: Event) => {
      img.removeEventListener("load", listener)
      c.success(img)
    }
    img.addEventListener("load", listener)
    c
  }

  def toDataURL(img: html.Image): Task[String] = loadImage(img).map { _ =>
    CanvasPool.withCanvas(img.width, img.height) { canvas =>
      val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
      ctx.drawImage(img, 0.0, 0.0)
      canvas.toDataURL("image/png")
    }
  }

  def preview(file: File): Task[Option[Image]] = if (Video.isVideo(file)) {
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
    Task.pure(None)
  }

  def preview(file: File, width: Double, height: Double, scaleUp: Boolean): Task[Option[Image]] = {
    preview(file).flatMap {
      case Some(image) => {
        val scaled = SizeUtility.scale(image.width, image.height, width, height, scaleUp)
        image.resize(scaled.width, scaled.height).map { resized =>
          image.dispose()
          Some(resized)
        }
      }
      case None => Task.pure(None)
    }
  }

  /**
    * Supports a Video or Image file and generates a smooth image preview for it.
    */
  def generatePreview(file: File, width: Double, height: Double, scaleUp: Boolean = false): Task[Option[String]] = {
    preview(file, width, height, scaleUp).flatMap {
      case Some(image) => {
        image.toDataURL.map(Some.apply)
      }
      case None => Task.pure(None)
    }
  }
}
