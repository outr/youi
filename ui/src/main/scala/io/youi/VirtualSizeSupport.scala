package io.youi

import com.outr.reactify._
import scribe._

trait VirtualSizeSupport {
  val virtualWidth: Var[Double] = Var[Double](1024.0)
  val virtualHeight: Var[Double] = Var[Double](768.0)
  val virtualMode: Var[VirtualMode] = Var[VirtualMode](VirtualMode.Bars)
  val virtual: VirtualSize = new VirtualSize(this)

  implicit class DoubleVirtualPixels(d: Double) {
    def vx: Val[Double] = Val[Double](vw() + virtual.xOffset())
    def vy: Val[Double] = Val[Double](vh() + virtual.yOffset())
    def vw: Val[Double] = Val[Double](d * virtual.widthMultiplier)
    def vh: Val[Double] = Val[Double](d * virtual.heightMultiplier)
    def vf: Val[Double] = vw
  }

  implicit class IntVirtualPixels(i: Int) {
    def vx: Val[Double] = Val[Double](vw() + virtual.xOffset())
    def vy: Val[Double] = Val[Double](vh() + virtual.yOffset())
    def vw: Val[Double] = Val[Double](i.toDouble * virtual.widthMultiplier)
    def vh: Val[Double] = Val[Double](i.toDouble * virtual.heightMultiplier)
    def vf: Val[Double] = vw
  }
}

sealed trait VirtualMode

object VirtualMode {
  case object Bars extends VirtualMode
  case object Clip extends VirtualMode
  case object Stretch extends VirtualMode
}

class VirtualSize(screen: VirtualSizeSupport,
                  width: State[Double] = UI.size.width,
                  height: State[Double] = UI.size.height) {
  private val size: Val[(Double, Double, Double, Double)] = Val {
    if (width > 0.0 && height > 0.0) {
      screen.virtualMode.get match {
        case VirtualMode.Bars | VirtualMode.Clip => {
          val widthRatio = width / screen.virtualWidth.get
          val heightRatio = height / screen.virtualHeight.get
          val ratio = screen.virtualMode.get match {
            case VirtualMode.Bars => math.min(widthRatio, heightRatio)
            case VirtualMode.Clip => math.max(widthRatio, heightRatio)
            case _ => 0.0   // Not possible
          }
          val w = screen.virtualWidth * ratio
          val h = screen.virtualHeight * ratio
          ((width - w) / 2.0, (height - h) / 2.0, ratio, ratio)
        }
        case VirtualMode.Stretch => {
          (0.0, 0.0, width / screen.virtualWidth, height / screen.virtualHeight)
        }
      }
    } else {
      (0.0, 0.0, 1.0, 1.0)
    }
  }
  val xOffset: Val[Double] = Val(size._1)
  val yOffset: Val[Double] = Val(size._2)
  val widthMultiplier: Val[Double] = Val(size._3)
  val heightMultiplier: Val[Double] = Val(size._4)
}