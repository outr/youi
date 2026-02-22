package io

import rapid.Task
import rapid.task.Completable
import io.youi.font.{GoogleFont, GoogleFontWeight, Phosphor}
import io.youi.paint.Paint
import io.youi.util.{CanvasPool, Time}
import org.scalajs.dom.{CanvasRenderingContext2D, document, html, window}
import reactify.Val
import typekit.{GoogleConfig, WebFont, WebFontConfiguration}

import scala.concurrent.duration._
import scala.language.implicitConversions
import scala.scalajs.js

package object youi {
  export reactify.stateful2Value

  def devicePixelRatio: Double = window.devicePixelRatio
  def backingStoreRatio: Double = CanvasPool.withCanvas(1.0, 1.0) { canvas =>
    def opt(d: js.Dynamic): Option[Double] = d.asInstanceOf[js.UndefOr[Double]].toOption
    val ctx = canvas.context.asInstanceOf[js.Dynamic]
    val webkit = opt(ctx.webkitBackingStorePixelRatio)
    val moz = opt(ctx.mozBackingStorePixelRatio)
    val ms = opt(ctx.msBackingStorePixelRatio)
    val o = opt(ctx.oBackingStorePixelRatio)
    val default = opt(ctx.backingStorePixelRatio)
    webkit.orElse(moz).orElse(ms).orElse(o).orElse(default).getOrElse(1.0)
  }
  def ratio: Double = devicePixelRatio / backingStoreRatio
  lazy val ppi: Double = {
    val div = dom.create[html.Div]("div")
    div.style.width = "1in"
    document.body.appendChild(div)
    try {
      div.offsetWidth
    } finally {
      document.body.removeChild(div)
    }
  }
  lazy val ua: String = window.navigator.userAgent.toLowerCase
  lazy val isIPhone: Boolean = ua.contains("iphone")
  lazy val isIPad: Boolean = ua.contains("ipad")
  lazy val isIOS: Boolean = isIPhone || isIPad
  lazy val isAndroid: Boolean = ua.contains("android")
  lazy val isMobileDevice: Boolean = isIOS || isAndroid

  private def waitForComputed(e: html.Element,
                              key: String,
                              delay: FiniteDuration)
                             (matcher: String => Boolean): Task[Unit] = {
    val value = window.getComputedStyle(e).getPropertyValue(key)
    if (matcher(value)) {
      Task.unit
    } else {
      Task.sleep(delay).flatMap(_ => waitForComputed(e, key, delay)(matcher))
    }
  }

  implicit def color2Paint(color: Color): Paint = Paint.color(color)

  implicit class ExtendedCanvas(canvas: html.Canvas) {
    def context: CanvasRenderingContext2D = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  }

  private lazy val phosphorTask: Task[Unit] = {
    val e = dom.create[html.Element]("i")
    e.classList.add("ph")
    e.classList.add("ph-house")
    e.style.visibility = "hidden"
    document.body.appendChild(e)
    dom.addCSS(Phosphor.url)
    waitForComputed(e, "font-family", 50.milliseconds) { value =>
      val b = value.contains("Phosphor")
      if (b) document.body.removeChild(e)
      b
    }
  }
  implicit class ExtendedPhosphor(phosphor: Phosphor.type) {
    def load(): Task[Unit] = phosphorTask
  }

  // Serialized font loading — webfontloader 1.6.26 drops callbacks when
  // multiple WebFont.load() calls are in flight concurrently.
  private var googleFonts = Map.empty[GoogleFont, Completable[GoogleFont]]
  private var fontLoadQueue: List[() => Unit] = Nil
  private var fontLoading: Boolean = false

  private def processNextFontLoad(): Unit = fontLoadQueue match {
    case Nil => fontLoading = false
    case next :: rest =>
      fontLoadQueue = rest
      next()
  }

  implicit class ExtendedGoogleFont(font: GoogleFont) {
    def load(): Task[GoogleFont] = googleFonts.get(font) match {
      case Some(c) =>
        scribe.info(s"Existing font: ${font.family}")
        c
      case None =>
        scribe.info(s"Queuing font: ${font.family}")
        val c: Completable[GoogleFont] = Task.completable[GoogleFont]
        def startLoad(): Unit = {
          scribe.info(s"Loading font: ${font.family}")
          val onActive: js.Function0[Unit] = () => {
            scribe.info(s"Font loaded: ${font.family}")
            c.success(font)
            processNextFontLoad()
          }
          val onInactive: js.Function0[Unit] = () => {
            scribe.warn(s"Font failed to load: ${font.family}, proceeding with fallback")
            c.success(font)
            processNextFontLoad()
          }
          WebFont.load(new WebFontConfiguration {
            google = new GoogleConfig {
              families = js.Array(font.family)
            }
            active = onActive
            inactive = onInactive
          })
        }
        fontLoadQueue = fontLoadQueue :+ (() => startLoad())
        if (!fontLoading) {
          fontLoading = true
          processNextFontLoad()
        }
        // TODO: Figure out why this breaks things
//        googleFonts += font -> c
        c
    }
  }

  implicit class ExtendedGoogleFontWeight(weight: GoogleFontWeight) {
    def load(): Task[GoogleFontWeight] = weight.font.load().map(_ => weight)
  }

  implicit class UINumericSize[T](t: T)(implicit n: Numeric[T]) {
    private val d = n.toDouble(t)

    /**
      * pixels
      */
    def px: Double = d

    /**
      * degrees conversion (360 converts to 1.0)
      */
    def degrees: Double = d / 360.0

    /**
      * radians conversion (2π converts to 1.0)
      */
    def radians: Double = d / (2.0 * math.Pi)

    /**
      * Returns percentage value `of`.
      */
    def percentOf(of: Val[Double]): Val[Double] = Val(of.get * (d * 0.01))

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
    def vmax: Val[Double] = Val(math.max((d / 100.0) * ui.size.width, (d / 100.0) * ui.size.height))

    /**
      * Returns percentage value `of`.
      */
    def %(of: Val[Double]): Val[Double] = percentOf(of)
  }
}
