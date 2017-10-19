package io.youi.component

import io.youi.Modifiable
import io.youi.drawable.{Cacheable, Context}
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import io.youi.util.LazyFuture
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ImageView extends Component with ImageViewTheme {
  /**
    * True if changing the image should automatically dispose the old `Image`.
    *
    * Defaults to true.
    */
  val autoDispose: Var[Boolean] = Var(true)

  /**
    * True if changing the size of the view should resize the `Image`.
    *
    * Defaults to true.
    */
  val autoSmooth: Var[Boolean] = Var(true)

  lazy val image: Var[Image] = {
    val v = Var(Image.empty)
    v.changes(new ChangeObserver[Image] {
      override def change(oldValue: Image, newValue: Image): Unit = {
        if (autoDispose()) {
          oldValue.dispose()
        }
      }
    })
    v
  }

  private lazy val resizer = LazyFuture {
    if (!autoSmooth() || (image.width == size.width() && image.height == size.height())) {
      Future.successful(())
    } else {
      image.resize(size.width, size.height).map { img =>
        image := img
      }
    }
  }

  size.width.and(size.height).on(resizer.flag())

  private object caching extends Cacheable

  override protected def init(): Unit = {
    super.init()

    updateMeasured(image.width, image.height)
  }

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)

  override protected def defaultThemeParent = Some(theme)

  override def `type`: String = "ImageView"

  override protected def modifiables: List[Modifiable] = super.modifiables ::: List(image())

//  override protected def drawInternal(context: Context): Unit = if (image.width > 0.0 && image.height > 0.0) {
//    if (size.width() == image.width && size.height() == image.height) {
//      // No scaling needed
//      image.drawFast(context, 0.0, 0.0, size.width, size.height)
//    } else if (caching.width.contains(size.width()) && caching.height.contains(size.height())) {
//      // Already cached
//      caching.draw(context, 0.0, 0.0)
//    } else {
//      // Need to scale, but we don't have a cached version
//      image.drawFast(context, 0.0, 0.0, size.width, size.height)
//      caching.updateCache(size.width, size.height) { context =>
//        image.drawAsync(context, 0.0, 0.0, size.width, size.height)
//      }.foreach { _ =>
//        modified := System.currentTimeMillis()
//      }
//    }
//  }

  override protected def drawInternal(context: Context): Unit = {
    image.draw(context, 0.0, 0.0, size.width, size.height)
  }

  init()
}

object ImageView extends ImageViewTheme