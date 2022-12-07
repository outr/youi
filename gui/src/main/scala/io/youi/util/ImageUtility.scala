package io.youi.util

import cats.effect.unsafe.implicits.global
import cats.effect.{Deferred, IO}
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
                   height: Double = source.asInstanceOf[html.Image].height): IO[html.Canvas] = {
    CanvasPool.withCanvasIO(width, height) { canvas =>
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
                    resizer: ImageResizer): IO[html.Image] = {
    resizeToDataURL(source, width, height, resizer).map { dataURL =>
      destination.src = dataURL
      destination
    }
  }

  def resizeToDataURL(source: html.Image | html.Canvas, width: Double, height: Double, resizer: ImageResizer): IO[String] = {
    val destinationCanvas = CanvasPool(width, height)
    val loader = if (source.asInstanceOf[html.Image].width == 0 || source.asInstanceOf[html.Image].height == 0) {
      loadImage(source.asInstanceOf[html.Image])
    } else {
      IO.pure(source.asInstanceOf[html.Image])
    }
    loader.flatMap { _ =>
      drawToCanvas(source, destinationCanvas, resizer)(
        width = destinationCanvas.width,
        height = destinationCanvas.height
      ).map { _ =>
        destinationCanvas.toDataURL("image/png")
      }
    }.guarantee(IO {
      CanvasPool.restore(destinationCanvas)
    })
  }

  private val tempImage = dom.create[html.Image]("img")

  def loadImage[R](file: File)(process: html.Image => IO[R]): IO[R] = {
    val d = Deferred[IO, Unit]
    val listener: js.Function1[Event, _] = (_: Event) => d.flatMap(_.complete(())).unsafeRunAndForget()
    tempImage.addEventListener("load", listener)
    FileUtility.loadDataURL(file).map(tempImage.src = _).unsafeRunAndForget()
    d.flatMap { d =>
      d.get.flatMap { _ =>
        tempImage.removeEventListener("load", listener)
        process(tempImage).guarantee(IO {
          tempImage.src = ""
        })
      }
    }
  }

  def loadImage(img: html.Image): IO[html.Image] = if (img.width > 0 && img.height > 0) {
    IO.pure(img)
  } else {
    val d = Deferred[IO, html.Image]
    var listener: js.Function1[Event, _] = null
    listener = (_: Event) => {
      d.flatMap(_.complete(img)).map { _ =>
        img.removeEventListener("load", listener)
      }.unsafeRunAndForget()
    }
    img.addEventListener("load", listener)
    d.flatMap(_.get)
  }

  def toDataURL(img: html.Image): IO[String] = loadImage(img).map { _ =>
    CanvasPool.withCanvas(img.width, img.height) { canvas =>
      val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
      ctx.drawImage(img, 0.0, 0.0)
      canvas.toDataURL("image/png")
    }
  }

  def preview(file: File): IO[Option[Image]] = if (Video.isVideo(file)) {
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
    IO.pure(None)
  }

  def preview(file: File, width: Double, height: Double, scaleUp: Boolean): IO[Option[Image]] = {
    preview(file).flatMap {
      case Some(image) => {
        val scaled = SizeUtility.scale(image.width, image.height, width, height, scaleUp)
        image.resize(scaled.width, scaled.height).map { resized =>
          image.dispose()
          Some(resized)
        }
      }
      case None => IO.pure(None)
    }
  }

  /**
    * Supports a Video or Image file and generates a smooth image preview for it.
    */
  def generatePreview(file: File, width: Double, height: Double, scaleUp: Boolean = false): IO[Option[String]] = {
    preview(file, width, height, scaleUp).flatMap {
      case Some(image) => {
        image.toDataURL.map(Some.apply)
      }
      case None => IO.pure(None)
    }
  }
}