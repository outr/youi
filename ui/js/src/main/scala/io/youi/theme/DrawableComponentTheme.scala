package io.youi.theme

import io.youi.paint.Border

trait DrawableComponentTheme extends CanvasComponentTheme {
  lazy val border: StyleProp[Border] = style[Border]("border", Border.empty, None, updatesTransform = true, updatesRendering = true)
}