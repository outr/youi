package youi.virtual

import youi.ui
import reactify.{Val, Var}

case class VirtualSize(virtualWidth: Double, virtualHeight: Double, mode: VirtualMode = VirtualMode.Bars) {
  val widthMultiplier: Val[Double] = Val(mode.widthMultiplier(virtualWidth, virtualHeight, ui.size.width(), ui.size.height()))
  val heightMultiplier: Val[Double] = Val(mode.heightMultiplier(virtualWidth, virtualHeight, ui.size.width(), ui.size.height()))
  val xOffset: Val[Double] = Val {
    val actual = ui.size.width()
    val scaled = virtualWidth * widthMultiplier()
    (actual - scaled) / 2.0
  }
  val yOffset: Val[Double] = Val {
    val actual = ui.size.height()
    val scaled = virtualHeight * heightMultiplier()
    (actual - scaled) / 2.0
  }

  def vx(value: Double): Val[Double] = Val(value * widthMultiplier() + xOffset())
  def vy(value: Double): Val[Double] = Val(value * heightMultiplier() + yOffset())
  def vw(value: Double): Val[Double] = Val(value * widthMultiplier())
  def vh(value: Double): Val[Double] = Val(value * heightMultiplier())
}

object VirtualSize {
  def withActualSize(virtualWidth: Double, virtualHeight: Double,
                     actualWidth: Val[Double], actualHeight: Val[Double],
                     mode: VirtualMode): VirtualSize = {
    val _vw = virtualWidth
    val _vh = virtualHeight
    val _aw = actualWidth
    val _ah = actualHeight
    val _mode = mode
    new VirtualSize(_vw, _vh, _mode) {
      override val widthMultiplier: Val[Double] = Val(_mode.widthMultiplier(_vw, _vh, _aw(), _ah()))
      override val heightMultiplier: Val[Double] = Val(_mode.heightMultiplier(_vw, _vh, _aw(), _ah()))
      override val xOffset: Val[Double] = Val {
        val actual = _aw()
        val scaled = _vw * widthMultiplier()
        (actual - scaled) / 2.0
      }
      override val yOffset: Val[Double] = Val {
        val actual = _ah()
        val scaled = _vh * heightMultiplier()
        (actual - scaled) / 2.0
      }
    }
  }
}

sealed trait VirtualMode {
  def widthMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double
  def heightMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double
}

object VirtualMode {
  case object Bars extends VirtualMode {
    def widthMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = {
      val s = math.min(aw / vw, ah / vh)
      s
    }
    def heightMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = widthMultiplier(vw, vh, aw, ah)
  }

  case object Clip extends VirtualMode {
    def widthMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = {
      val s = math.max(aw / vw, ah / vh)
      s
    }
    def heightMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = widthMultiplier(vw, vh, aw, ah)
  }

  case object Stretch extends VirtualMode {
    def widthMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = aw / vw
    def heightMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = ah / vh
  }

  case object FitWidth extends VirtualMode {
    def widthMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = aw / vw
    def heightMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = widthMultiplier(vw, vh, aw, ah)
  }

  case object FitHeight extends VirtualMode {
    def widthMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = heightMultiplier(vw, vh, aw, ah)
    def heightMultiplier(vw: Double, vh: Double, aw: Double, ah: Double): Double = ah / vh
  }
}
