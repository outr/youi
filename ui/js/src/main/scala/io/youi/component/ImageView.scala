package io.youi.component

import io.youi.Modifiable
import io.youi.drawable.{Cacheable, Context}
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global

class ImageView extends Component with ImageViewTheme {
  lazy val image: Var[Image] = Var(Image.empty)

  private object caching extends Cacheable

  updateMeasured(image.width, image.height)

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)

  override protected def defaultThemeParent = Some(theme)

  override protected def modifiables: List[Modifiable] = super.modifiables ::: List(image())

  override protected def drawInternal(context: Context): Unit = if (image.width > 0.0 && image.height > 0.0) {
    if (size.width() == image.width && size.height() == image.height) {
      // No scaling needed
      image.drawFast(context, 0.0, 0.0, size.width, size.height)
    } else if (caching.width.contains(size.width()) && caching.height.contains(size.height())) {
      // Already cached
      caching.draw(context, 0.0, 0.0)
    } else {
      // Need to scale, but we don't have a cached version
      image.drawFast(context, 0.0, 0.0, size.width, size.height)
      caching.updateCache(size.width, size.height) { context =>
        image.drawAsync(context, 0.0, 0.0, size.width, size.height)
      }.foreach { _ =>
        modified := System.currentTimeMillis()
      }
    }
  }
}

object ImageView extends ImageViewTheme