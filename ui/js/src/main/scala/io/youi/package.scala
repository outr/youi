package io

import io.youi.style.Paint
import org.scalajs.dom.html
import org.scalajs.dom.raw.{CanvasRenderingContext2D, WebGLRenderingContext}
import reactify._

import scala.language.implicitConversions

package object youi {
  def ui: UI.type = UI

  implicit def color2Paint(color: Color): Paint = Paint.color(color)

  implicit class CanvasExtras(canvas: html.Canvas) {
    def context: CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    def webGL: WebGLRenderingContext = canvas.getContext("webgl").asInstanceOf[WebGLRenderingContext]
  }

  implicit class NumericSize[T](t: T)(implicit n: Numeric[T]) {
    private val d = n.toDouble(t)

    /**
      * pixels
      */
    def px: Double = d

    /**
      * millimeters
      */
    def mm: Double = in * 25.4

    /**
      * quarter millimeters
      */
    def q: Double = mm * 0.25

    /**
      * centimeters
      */
    def cm: Double = mm * 10.0

    /**
      * inches
      */
    def in: Double = d * ui.ppi

    /**
      * points
      */
    def pt: Double = in / 72.0

    /**
      * picas
      */
    def pica: Double = pt * 12.0

    /**
      * degrees conversion (360 converts to 1.0)
      */
    def degrees: Double = d / 360.0

    /**
      * radians conversion (2π converts to 1.0)
      */
    def radians: Double = d / (2.0 * math.Pi)

    /**
      * 1/100th of the width of the viewport.
      */
    def vw: Val[Double] = Val((d / 100.0) * ui.size.width)

    /**
      * 1/100th of the height of the viewport.
      */
    def vh: Val[Double] = Val((d / 100.0) * ui.size.height)

    /**
      * 1/100th of the minimum value between the height and the width of the viewport.
      */
    def vmin: Val[Double] = Val(math.min((d / 100.0) * ui.size.width, (d / 100.0) * ui.size.height))

    /**
      * 1/100th of the maximum value between the height and the width of the viewport.
      */
    def vmax: Val[Double] = Val[Double](math.max((d / 100.0) * ui.size.width, (d / 100.0) * ui.size.height))

    /**
      * Returns percentage value `of`.
      */
    def %(of: State[Double]): Val[Double] = percentOf(of)

    /**
      * Returns percentage value `of`.
      */
    def percentOf(of: State[Double]): Val[Double] = Val(of.get * (d * 0.01))
  }
}