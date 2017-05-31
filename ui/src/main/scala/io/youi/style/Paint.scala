package io.youi.style

import io.youi.component.{Component, Renderer}
import io.youi.net.URL
import io.youi.{Color, dom}
import org.scalajs.dom.raw.CanvasRenderingContext2D
import org.scalajs.dom.{CanvasGradient, CanvasPattern, Event, html}

import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.{UndefOr, |}

trait Paint {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty
  def apply(component: Component): js.UndefOr[String | js.Array[String] | Double | CanvasGradient | CanvasPattern] = value(component, Paint.context)

  protected def value(component: Component, context: CanvasRenderingContext2D): js.UndefOr[String | js.Array[String] | Double | CanvasGradient | CanvasPattern]
}

object NoPaint extends Paint {
  override def isEmpty = true
  override protected  def value(component: Component, context: CanvasRenderingContext2D): js.UndefOr[String | js.Array[String] | Double | CanvasGradient | CanvasPattern] = null
}

class ColorPaint(color: Color) extends Paint {
  override protected  def value(component: Component, context: CanvasRenderingContext2D): js.UndefOr[String | js.Array[String] | Double | CanvasGradient | CanvasPattern] = color.css
}

class LinearGradientPaint(direction: GradientDirection, stops: Vector[GradientStop]) extends Paint {
  override protected  def value(component: Component, context: CanvasRenderingContext2D): js.UndefOr[String | js.Array[String] | Double | CanvasGradient | CanvasPattern] = {
    val info = direction.info(component)
    val g = context.createLinearGradient(info.begin.x, info.begin.y, info.end.x, info.end.y)
    stops.foreach { stop =>
      g.addColorStop(stop.offset, stop.color.hex)
    }
    g
  }
}

class RadialGradientPaint(x0: => Double,
                          y0: => Double,
                          r0: => Double,
                          x1: => Double,
                          y1: => Double,
                          r1: => Double,
                          stops: Vector[GradientStop]) extends Paint {
  override protected  def value(component: Component, context: CanvasRenderingContext2D): js.UndefOr[String | js.Array[String] | Double | CanvasGradient | CanvasPattern] = {
    val g = context.createRadialGradient(x0, y0, r0, x1, y1, r1)
    stops.foreach { stop =>
      g.addColorStop(stop.offset, stop.color.hex)
    }
    g
  }
}

class PatternPaint(pattern: CanvasPattern) extends Paint {
  override protected def value(component: Component, context: CanvasRenderingContext2D): js.UndefOr[String | js.Array[String] | Double | CanvasGradient | CanvasPattern] = pattern
}

object Paint {
  private lazy val canvas = dom.create[html.Canvas]("canvas")
  private lazy val context = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def none: Paint = NoPaint
  def color(color: Color): Paint = new ColorPaint(color)
  def horizontal(colors: Color*): Paint = linear(GradientDirection.Horizontal, colors: _*)
  def vertical(colors: Color*): Paint = linear(GradientDirection.Vertical, colors: _*)
  def linear(direction: GradientDirection, colors: Color*): Paint = {
    val length = colors.length
    val adjust = 1.0 / (length.toDouble - 1)
    var offset = 0.0
    val stops = colors.map { color =>
      val stop = GradientStop(color, offset)
      offset += adjust
      stop
    }
    new LinearGradientPaint(direction, stops.toVector)
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
    new RadialGradientPaint(x0, y0, r0, x1, y1, r1, stops.toVector)
  }

  def image(url: String | URL, repetition: Repetition = Repetition.Repeat): Future[Paint] = {
    val promise = Promise[Paint]
    val img = dom.create[html.Image]("img")
    img.addEventListener("load", (_: Event) => {
      val pattern = context.createPattern(img, repetition.value)
      promise.success(new PatternPaint(pattern))
    })
    img.src = url.toString
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