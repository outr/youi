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
import scala.scalajs.js.typedarray.Float32Array

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
      scribe.info(s"Resize to canvas (smooth), ${destination.width} x ${destination.height}")
      picaFuture {
        pica.resize(source, destination, new ResizeOptions {
          alpha = true
        }).toFuture
      }
    } else {
      scribe.info(s"Resize to canvas (fast), ${destination.width} x ${destination.height}")
      destination.context.drawImage(source.asInstanceOf[html.Image], 0.0, 0.0, destination.width, destination.height)
      Future.successful(destination)
    }
  }

  // TODO: fix it so it can be used
  private def downSampleCanvas[R](cv: html.Canvas, scale: Double)(f: html.Canvas => R): R = {
    assert(scale > 0.0, s"Scale must be a positive number ($scale).")
    assert(scale < 1.0, s"Scale must be less than 1.0 ($scale).")

    val sqScale = scale * scale
    val sw = cv.width
    val sh = cv.height
    val tw = math.floor(sw * scale).toInt
    val th = math.floor(sh * scale).toInt
    val sBuffer = cv.context.getImageData(0.0, 0.0, sw, sh).data
    val tBuffer = new Float32Array(3 * tw * th)

    var sIndex = 0
    var wx = 0.0
    var wy = 0.0
    var nwx = 0.0
    var nwy = 0.0

    var w = 0.0
    var nw = 0.0

    (0 until sh).foreach { sy =>
      val ty = sy * scale
      val tY = 0 | ty.toInt
      val yIndex = 3 * tY * tw
      val crossY = tY != (0 | (ty + scale).toInt)
      if (crossY) {
        wy = tY + 1.0 - ty
        nwy = ty + scale - tY - 1.0
      }
      (0 until sw).foreach { sx =>
        sIndex += 4
        val tx = sx * scale
        val tX = 0 | tx.toInt
        val tIndex = yIndex + tX * 3
        val crossX = tX != (0 | (tx + scale).toInt)
        if (crossX) {
          wx = tX + 1.0 - tx
          nwx = tx + scale - tX - 1.0
        }
        val sR = sBuffer(sIndex)
        val sG = sBuffer(sIndex + 1)
        val sB = sBuffer(sIndex + 2)
        if (!crossX && !crossY) {
          scribe.info("1a")
          tBuffer(tIndex) += (sR * sqScale).toFloat
          tBuffer(tIndex + 1) += (sG * sqScale).toFloat
          tBuffer(tIndex + 2) + (sB * sqScale).toFloat
        } else if (crossX && !crossY) {
          scribe.info("1b")
          w = wx * scale
          tBuffer(tIndex) += (sR * w).toFloat
          tBuffer(tIndex + 1) += (sG * w).toFloat
          tBuffer(tIndex + 2) += (sB * w).toFloat

          nw = nwx * scale
          tBuffer(tIndex + 3) += (sR * nw).toFloat
          tBuffer(tIndex + 4) += (sG * nw).toFloat
          tBuffer(tIndex + 5) += (sB * nw).toFloat
        } else if (!crossX && crossY) {
          scribe.info("1c")
          w = wy * scale
          tBuffer(tIndex) += (sR * w).toFloat
          tBuffer(tIndex + 1) += (sG * w).toFloat
          tBuffer(tIndex + 2) += (sB * w).toFloat
          nw = nwy * scale
          tBuffer(tIndex + 3 * tw) += (sR * nw).toFloat
          tBuffer(tIndex + 3 * tw + 1) += (sG * nw).toFloat
          tBuffer(tIndex + 3 * tw + 2) += (sB * nw).toFloat
        } else {
          scribe.info("1d")
          w = wx * wy
          tBuffer(tIndex) += (sR * w).toFloat
          tBuffer(tIndex + 1) += (sG * w).toFloat
          tBuffer(tIndex + 2) += (sB * w).toFloat
          nw = nwx * wy
          tBuffer(tIndex + 3) += (sR * nw).toFloat
          tBuffer(tIndex + 4) += (sG * nw).toFloat
          tBuffer(tIndex + 5) += (sB * nw).toFloat
          nw = wx * nwy
          tBuffer(tIndex + 3 * tw) += (sR * nw).toFloat
          tBuffer(tIndex + 3 * tw + 1) += (sG * nw).toFloat
          tBuffer(tIndex + 3 * tw + 2) += (sB * nw).toFloat
          nw = nwx * nwy
          tBuffer(tIndex + 3 * tw + 3) += (sR * nw).toFloat
          tBuffer(tIndex + 3 * tw + 4) += (sG * nw).toFloat
          tBuffer(tIndex + 3 * tw + 5) += (sB * nw).toFloat
        }
      }
    }

    CanvasPool.withCanvas(tw, th) { resCV =>
      val resCtx = resCV.context
      val imgRes = resCtx.getImageData(0.0, 0.0, tw, th)
      val tByteBuffer = imgRes.data
      var sIndex = 0
      var tIndex = 0
      (0 until tw * th).foreach { pxIndex =>
        sIndex += 3
        tIndex += 4
        tByteBuffer(tIndex) = 0 | tBuffer(sIndex).toInt
        tByteBuffer(tIndex + 1) = 0 | tBuffer(sIndex + 1).toInt
        tByteBuffer(tIndex + 2) = 0 | tBuffer(sIndex + 2).toInt
        tByteBuffer(tIndex + 3) = 255
      }
      resCtx.putImageData(imgRes, 0.0, 0.0)
      f(resCV)
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
    scribe.info(s"Resize to image! $width x $height")
    resizeToDataURL(source, width, height).map { dataURL =>
      destination.src = dataURL
      destination
    }
  }

  def resizeToDataURL(source: html.Image | html.Canvas, width: Double, height: Double): Future[String] = {
    scribe.info(s"Resize to Data URL! $width x $height")
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
    scribe.info("Loading image!")
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

  def loadDataURL(file: File, useFileReader: Boolean = true): Future[String] = if (useFileReader) {
    scribe.info("Load data url using FileReader!")
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
    scribe.info("Load data url using Object URL!")
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
    scribe.info(s"generatePreview! $width x $height")
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