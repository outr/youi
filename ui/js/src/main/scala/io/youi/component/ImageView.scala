package io.youi.component

import io.youi.theme.ImageViewTheme
import reactify._

class ImageView extends Component with ImageViewTheme {
  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)

  override protected def defaultThemeParent = Some(theme)
}

object ImageView extends ImageViewTheme