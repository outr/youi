package youi.image.resize

import rapid.Task
import org.scalajs.dom.html

import scala.scalajs.js.|

trait ImageResizer {
  final def resize(source: html.Image | html.Canvas, destination: html.Canvas): Task[html.Canvas] = {
    val src = source.asInstanceOf[html.Image]
    val srcWidth = src.width
    val srcHeight = src.height
    if (srcWidth <= 0 || srcHeight <= 0 || destination.width <= 0 || destination.height <= 0) {
      Task.pure(destination)
    } else if (srcWidth == destination.width && srcHeight == destination.height) {
      FastResizer.resizeInternal(source, destination)
    } else {
      resizeInternal(source, destination)
    }
  }
  protected def resizeInternal(source: html.Image | html.Canvas, destination: html.Canvas): Task[html.Canvas]
}

object ImageResizer {
  /**
    * Defines the default smoothing implementation to use.
    *
    * Defaults to using `StepDown`.
    */
  var Smooth: ImageResizer = SmoothHigh

  def Fast: ImageResizer = FastResizer
//  def Pica: ImageResizer = PicaResizer
  def StepDown: ImageResizer = StepDownResizer

  // NOTE: Only supported in some browsers (see https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/imageSmoothingQuality)
  // TODO: determine a way to detect support for this (Firefox defaults to `high` even though it's not supported)
  lazy val SmoothLow: ImageResizer = new SmoothingResizer("low")
  lazy val SmoothMedium: ImageResizer = new SmoothingResizer("medium")
  lazy val SmoothHigh: ImageResizer = new SmoothingResizer("high")
}