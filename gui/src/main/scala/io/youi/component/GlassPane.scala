package io.youi.component

import io.youi.component.support.{PositionSupport, SizeSupport}
import io.youi.component.types.{Display, PositionType}
import io.youi.easing.Easing
import io.youi.event.EventSupport
import io.youi.task._
import io.youi.{Color, dom, ui}
import reactify.Var

import scala.concurrent.duration._

class GlassPane extends Component(dom.create.div) with SizeSupport with PositionSupport with EventSupport {
  val backgroundAlpha: Var[Double] = Var(0.4)

  def isActive: Boolean = display() != Display.None

  position.`type` @= PositionType.Absolute
  position.x @= 0.0
  position.y @= 0.0
  position.z @= 1500
  size.width := ui.size.width
  size.height := ui.size.height
  backgroundColor := Color.Black.withAlpha(backgroundAlpha)
  display @= Display.None

  def show(fadeIn: Boolean): Unit = {
    if (fadeIn) {
      sequential(
        backgroundAlpha @= 0.0,
        display @= Display.Block,
        backgroundAlpha to 0.5 in 250.millis easing Easing.exponentialOut
      ).start()
    } else {
      display @= Display.Block
    }
  }

  def hide(fadeOut: Boolean): Unit = {
    if (fadeOut) {
      sequential(
        backgroundAlpha to 0.0 in 1000.millis easing Easing.exponentialOut,
        display @= Display.None
      ).start()
    } else {
      display @= Display.None
    }
  }
}