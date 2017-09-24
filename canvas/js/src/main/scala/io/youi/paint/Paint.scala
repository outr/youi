package io.youi.paint

import io.youi.{Color, Context, dom}
import io.youi.component.Component
import io.youi.net.URL
import io.youi.util.CanvasPool
import org.scalajs.dom.raw.CanvasRenderingContext2D
import org.scalajs.dom.{CanvasPattern, Event, html}

import scala.concurrent.{Future, Promise}
import scala.scalajs._
import scala.scalajs.js.|

trait Paint {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty
  def update(): Unit = {}
  def modified: Long = 0L
  def asJS(context: Context): js.Any
}

object Paint {
  def none: Paint = NoPaint
  def color(color: Color): Paint = ColorPaint(color)
  def horizontal(component: Component): LinearGradientPaint = horizontal(component.size.width)
  def horizontal(width: Double): LinearGradientPaint = linear(0.0, 0.0, width, 0.0)
  def vertical(component: Component): LinearGradientPaint = vertical(component.size.height)
  def vertical(height: Double): LinearGradientPaint = linear(0.0, 0.0, 0.0, height)
  def linear(x0: Double, y0: Double, x1: Double, y1: Double): LinearGradientPaint = {
    LinearGradientPaint(x0, y0, x1, y1)
  }
  def radial(x0: Double, y0: Double, r0: Double, x1: Double, y1: Double, r1: Double): RadialGradientPaint = {
    RadialGradientPaint(x0, y0, r0, x1, y1, r1)
  }
  def component(component: Component, repetition: Repetition = Repetition.Repeat): Paint = {
    new ComponentPaint(component, repetition)
  }
  def image(url: String | URL, repetition: Repetition = Repetition.Repeat): Future[Paint] = {
    val promise = Promise[Paint]
    val img = dom.create[html.Image]("img")
    img.addEventListener("load", (_: Event) => {
      CanvasPool.withCanvas(img.width, img.height) { canvas =>
        val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
        val pattern = context.createPattern(img, repetition.value)
        promise.success(new PatternPaint {
          override def createPattern(context: CanvasRenderingContext2D): CanvasPattern = pattern
        })
      }
    })
    img.src = url.toString
    promise.future
  }

  def video(url: String | URL,
            repetition: Repetition = Repetition.Repeat,
            muted: Boolean = true): Future[Paint] = {
    val promise = Promise[Paint]
    val video = dom.create[html.Video]("video")
    video.autoplay = true
    video.muted = muted
    video.addEventListener("loadedmetadata", (_: Event) => {
      scribe.info(s"Video Size: ${video.videoWidth}x${video.videoHeight}")
      val create = (context: CanvasRenderingContext2D) => {
        context.createPattern(video, repetition.value)
      }
      promise.success(new PatternPaint {
        override def createPattern(context: CanvasRenderingContext2D): CanvasPattern = create(context)
      })
    })
    video.src = url.toString
    promise.future
  }
}