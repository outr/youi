package io.youi.theme

trait DrawableComponentTheme extends CanvasComponentTheme {
  lazy val border: StyleProp[Border] = style[Border]("border", Border.empty, None, updatesTransform = true, updatesRendering = true)
}