package io.youi.theme

trait CanvasComponentTheme extends HTMLComponentTheme {
  val ratio: StyleProp[Double] = style[Double]("ratio", ui.ratio, None, updatesTransform = true, updatesRendering = true)
}