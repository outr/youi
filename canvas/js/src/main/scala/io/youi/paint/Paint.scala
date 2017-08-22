package io.youi.paint

import io.youi.{Color, dom}
import io.youi.component.Component
import io.youi.net.URL
import io.youi.util.CanvasPool
import org.scalajs.dom.raw.CanvasRenderingContext2D
import org.scalajs.dom.{CanvasPattern, Event, html}

import scala.concurrent.{Future, Promise}
import scala.scalajs.js.|

sealed trait Paint {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty
}

object NoPaint extends Paint {
  override def isEmpty = true

  override def toString: String = "NoPaint"
}

case class ColorPaint(color: Color) extends Paint {
  override def toString: String = color.toString
}

case class LinearGradientPaint(component: Component,
                               direction: GradientDirection,
                               stops: Vector[GradientStop]) extends Paint

case class RadialGradientPaint(x0: Double,
                               y0: Double,
                               r0: Double,
                               x1: Double,
                               y1: Double,
                               r1: Double,
                               stops: Vector[GradientStop]) extends Paint

case class PatternPaint(createPattern: CanvasRenderingContext2D => CanvasPattern) extends Paint

object Paint {
  def none: Paint = NoPaint
  def color(color: Color): Paint = ColorPaint(color)
  def horizontal(component: Component, colors: Color*): Paint = linear(component, GradientDirection.Horizontal, colors: _*)
  def vertical(component: Component, colors: Color*): Paint = linear(component, GradientDirection.Vertical, colors: _*)
  def linear(component: Component, direction: GradientDirection, colors: Color*): Paint = {
    val length = colors.length
    val adjust = 1.0 / (length.toDouble - 1)
    var offset = 0.0
    val stops = colors.map { color =>
      val stop = GradientStop(color, offset)
      offset += adjust
      stop
    }
    LinearGradientPaint(component, direction, stops.toVector)
  }

  // TODO: simplify this into a radial gradient builder
  def radial(component: Component, colors: Color*)(x0: => Double = component.size.center(),
                                                   y0: => Double = -component.size.middle(),
                                                   r0: => Double = 0.0,
                                                   x1: => Double = component.size.center(),
                                                   y1: => Double = -component.size.middle(),
                                                   r1: => Double = math.max(component.size.center(), component.size.middle)): Paint = {
    val length = colors.length
    val adjust = 1.0 / (length.toDouble - 1)
    var offset = 0.0
    val stops = colors.map { color =>
      val stop = GradientStop(color, offset)
      offset += adjust
      stop
    }
    RadialGradientPaint(x0, y0, r0, x1, y1, r1, stops.toVector)
  }

  def image(url: String | URL, repetition: Repetition = Repetition.Repeat): Future[Paint] = {
    val promise = Promise[Paint]
    val img = dom.create[html.Image]("img")
    img.addEventListener("load", (_: Event) => {
      CanvasPool.withCanvas(img.width, img.height) { canvas =>
        val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
        val pattern = context.createPattern(img, repetition.value)
        promise.success(PatternPaint(_ => pattern))
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
      val createPattern = (context: CanvasRenderingContext2D) => {
        context.createPattern(video, repetition.value)
      }
      promise.success(PatternPaint(createPattern))
    })
    video.src = url.toString
    promise.future
  }
}

sealed abstract class Repetition(val value: String)

object Repetition {
  case object Repeat extends Repetition("repeat")
  case object RepeatX extends Repetition("repeat-x")
  case object RepeatY extends Repetition("repeat-y")
  case object NoRepeat extends Repetition("no-repeat")
}