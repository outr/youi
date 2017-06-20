package io.youi.theme

import io.youi.component.TextureComponent

trait CanvasComponentTheme extends ImageTheme {
  override def defaultParent: Option[Theme] = Some(TextureComponent)
}
