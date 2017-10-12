package io.youi.component

import io.youi.Modifiable
import io.youi.drawable.Context
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import reactify._

class ImageView extends Component with ImageViewTheme {
  lazy val image: Var[Image] = Var(Image.empty)

  updateMeasured(image.width, image.height)

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)

  override protected def defaultThemeParent = Some(theme)

  override protected def modifiables: List[Modifiable] = super.modifiables ::: List(image())

  override protected def drawInternal(context: Context): Unit = {
    image.draw(context, 0.0, 0.0)
  }
}

object ImageView extends ImageViewTheme