package io.youi

import io.youi.component.Container
import io.youi.event.{EventSupport, HTMLEvents}
import io.youi.style.{Display, Visibility}
import io.youi.util.CanvasPool
import org.scalajs.dom.{Event, document, window}
import reactify._

import scala.concurrent.duration._
import scala.scalajs.js

object ui extends Container(document.body) {
  id := "ui"

  override val visible: Val[Boolean] = Val(visibility() == Visibility.Visible && display() != Display.None)

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
  lazy val displayRatio: Val[Double] = Var(devicePixelRatio / backingStoreRatio)
  lazy val ratio: Var[Double] = Var(displayRatio)

  lazy val title: Var[String] = Var(document.title)

  override val event: EventSupport = new HTMLEvents(this, window)

  event.key.down.attach { evt =>
    evt.key.pressed.asInstanceOf[Var[Boolean]] := true
  }
  event.key.up.attach { evt =>
    evt.key.pressed.asInstanceOf[Var[Boolean]] := false
  }

  title.attach(document.title = _)

  window.addEventListener("resize", (_: Event) => {
    size.measured.width := window.innerWidth
    size.measured.height := window.innerHeight
  })

  AnimationFrame.every(1.second) {
    val r = devicePixelRatio / backingStoreRatio
    if (r != displayRatio()) {
      displayRatio.asInstanceOf[Var[Double]] := r
    }
  }

  AnimationFrame.delta.attach { d =>
    update(d)
  }

  override protected def measuredWidth: Double = window.innerWidth

  override protected def measuredHeight: Double = window.innerHeight
}