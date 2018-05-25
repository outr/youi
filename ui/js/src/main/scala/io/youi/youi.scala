package io

import io.youi.event.KeyEvent
import io.youi.font.{GoogleFont, GoogleFontWeight}
import io.youi.paint.Paint
import io.youi.style.{FontFamily, FontWeight, Length}
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.CanvasRenderingContext2D
import org.scalajs.dom.{KeyboardEvent, document, html}
import reactify._
import typekit.{GoogleConfig, WebFont, WebFontConfiguration}

import scala.concurrent.{Future, Promise}
import scala.language.implicitConversions
import scala.scalajs.js

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

  implicit def gf2FontFamily(gf: GoogleFont): FontFamily = FontFamily(gf.family)

  implicit def gfw2FontFamily(gfw: GoogleFontWeight): FontFamily = FontFamily(gfw.font.family)
  implicit def gfw2FontWeight(gfw: GoogleFontWeight): FontWeight = FontWeight(gfw.name)

  implicit class ExtendedGoogleFont(font: GoogleFont) {
    def load(): Future[GoogleFont] = {
      val promise = Promise[GoogleFont]
      val f: js.Function0[Unit] = () => {
        promise.success(font)
        ()
      }
      WebFont.load(new WebFontConfiguration {
        google = new GoogleConfig {
          families = js.Array(font.family)
        }
        active = f
      })
      promise.future
    }
  }

  implicit class ExtendedGoogleFontWeight(weight: GoogleFontWeight) {
    def load(): Future[GoogleFontWeight] = {
      val promise = Promise[GoogleFontWeight]
      val f: js.Function0[Unit] = () => {
        promise.success(weight)
        ()
      }
      WebFont.load(new WebFontConfiguration {
        google = new GoogleConfig {
          families = js.Array(s"${weight.font.family}:${weight.name}")
        }
        active = f
      })
      promise.future
    }
  }

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

  implicit def double2Length(d: Double): Length = Length(d)
  implicit def length2Double(l: Length): Double = l.value

  implicit class UINumericSize[T](t: T)(implicit n: Numeric[T]) {
    private val d = n.toDouble(t)

    /**
      * pixels
      */
    def px: Length = Length(d)

    /**
      * degrees conversion (360 converts to 1.0)
      */
    def degrees: Length = d / 360.0

    /**
      * radians conversion (2π converts to 1.0)
      */
    def radians: Length = d / (2.0 * math.Pi)

    /**
      * Returns percentage value `of`.
      */
    def percentOf(of: State[Length]): Val[Length] = Val(of.get * (d * 0.01))

    /**
      * millimeters
      */
    def mm: Length = in * 25.4

    /**
      * quarter millimeters
      */
    def q: Length = mm * 0.25

    /**
      * centimeters
      */
    def cm: Length = mm * 10.0

    /**
      * inches
      */
    def in: Length = d * ppi

    /**
      * points
      */
    def pt: Length = in / 72.0

    /**
      * picas
      */
    def pica: Length = pt * 12.0

    /**
      * 1/100th of the width of the viewport.
      */
    def vw: Val[Length] = Val((d / 100.0) * ui.size.actual.width)

    /**
      * 1/100th of the height of the viewport.
      */
    def vh: Val[Length] = Val((d / 100.0) * ui.size.actual.height)

    /**
      * 1/100th of the minimum value between the height and the width of the viewport.
      */
    def vmin: Val[Length] = Val(math.min((d / 100.0) * ui.size.actual.width, (d / 100.0) * ui.size.actual.height))

    /**
      * 1/100th of the maximum value between the height and the width of the viewport.
      */
    def vmax: Val[Length] = Val[Length](math.max((d / 100.0) * ui.size.actual.width, (d / 100.0) * ui.size.actual.height))

    /**
      * Returns percentage value `of`.
      */
    def %(of: State[Length]): Val[Length] = percentOf(of)
  }
}