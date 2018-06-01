package io.youi.theme

import io.youi.ui

trait CanvasComponentTheme extends HTMLComponentTheme {
  val ratio: StyleProp[Double] = style[Double]("ratio", ui.ratio, None, updatesTransform = true, updatesRendering = true)
}