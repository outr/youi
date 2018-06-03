package io.youi.virtual

import reactify._

class VirtualSize(screen: VirtualSizeSupport) {
  private val size: Val[(Double, Double, Double, Double)] = Val {
    if (screen.actual.width > 0.0 && screen.actual.height > 0.0) {
      screen.virtualMode.get match {
        case VirtualMode.Bars | VirtualMode.Clip | VirtualMode.FitWidth | VirtualMode.FitHeight => {
          val widthRatio = screen.actual.width / screen.virtualWidth.get
          val heightRatio = screen.actual.height / screen.virtualHeight.get
          val ratio = screen.virtualMode.get match {
            case VirtualMode.Bars => math.min(widthRatio, heightRatio)
            case VirtualMode.Clip => math.max(widthRatio, heightRatio)
            case VirtualMode.FitWidth => widthRatio
            case VirtualMode.FitHeight => heightRatio
            case _ => 0.0   // Not possible
          }
          val w = screen.virtualWidth * ratio
          val h = screen.virtualHeight * ratio
          ((screen.actual.width - w) / 2.0, (screen.actual.height - h) / 2.0, ratio, ratio)
        }
        case VirtualMode.Stretch => {
          (0.0, 0.0, screen.actual.width / screen.virtualWidth, screen.actual.height / screen.virtualHeight)
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