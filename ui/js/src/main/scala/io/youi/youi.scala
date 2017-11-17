package io

import io.youi.event.KeyEvent
import io.youi.paint.Paint
import io.youi.spatial.NumericSize
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.CanvasRenderingContext2D
import org.scalajs.dom.{KeyboardEvent, document, html}
import reactify._

import scala.language.implicitConversions

package object youi {
  lazy val ppi: Double = {
    val div = dom.create[Div]("div")
    div.style.width = "1in"
    document.body.appendChild(div)
    try {
      div.offsetWidth
    } finally {
      document.body.removeChild(div)
    }
  }

  implicit def color2Paint(color: Color): Paint = Paint.color(color)

  implicit class ExtendedCanvas(canvas: html.Canvas) {
    def context: CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  }

  implicit class ExtendedKeyboardEvent(evt: KeyboardEvent) {
    def toKeyEvent(`type`: KeyEvent.Type): Option[KeyEvent] = {
      Key.get(evt.key).map { key =>
        KeyEvent(
          `type` = `type`,
          key = key,
          repeat = evt.repeat,
          modifierState = (k: Key) => evt.getModifierState(k.value),
          preventDefault = () => evt.preventDefault(),
          defaultPrevented = () => evt.defaultPrevented,
          stopImmediatePropagation = () => evt.stopImmediatePropagation(),
          stopPropagation = () => evt.stopPropagation()
        )
      }
    }
  }

  implicit class UINumericSize[T](t: T)(implicit n: Numeric[T]) extends NumericSize[T](t)(n) {
    private val d = n.toDouble(t)

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
    def in: Double = d * ppi

    /**
      * points
      */
    def pt: Double = in / 72.0

    /**
      * picas
      */
    def pica: Double = pt * 12.0

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
  }
}