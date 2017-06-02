package io.youi.theme

import io.youi.component.Image

trait CanvasComponentTheme extends ImageTheme {
  override def defaultParent: Option[Theme] = Some(Image)
}
