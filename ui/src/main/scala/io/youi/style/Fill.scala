package io.youi.style

import com.outr.pixijs.PIXI.TextStyle
import com.outr.pixijs._
import io.youi.Color

import scala.scalajs.js

trait Fill {
  protected[youi] def fill(textStyle: PIXI.TextStyle): Unit
}

case class ColorFill(color: Color) extends Fill {
  override protected[youi] def fill(textStyle: TextStyle): Unit = {
    textStyle.fill = Color.toHex(color)
  }
}

class GradientFill(direction: GradientDirection, stops: Vector[GradientStop]) extends Fill {
  override protected[youi] def fill(textStyle: TextStyle): Unit = {
    textStyle.fill = js.Array[String](stops.map(_.color.hex): _*)
    textStyle.fillGradientType = direction match {
      case GradientDirection.Vertical => PIXI.TEXT_GRADIENT.LINEAR_VERTICAL
      case GradientDirection.Horizontal => PIXI.TEXT_GRADIENT.LINEAR_HORIZONTAL
    }
    textStyle.fillGradientStops = js.Array[Double](stops.map(_.stop): _*)
  }
}

object GradientFill {
}

sealed trait GradientDirection

object GradientDirection {
  case object Vertical extends GradientDirection
  case object Horizontal extends GradientDirection
}

case class GradientStop(color: Color, stop: Double)