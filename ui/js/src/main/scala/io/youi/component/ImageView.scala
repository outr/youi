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

  private lazy val resizer = LazyFuture({
    if (!autoSmooth() ||
        (image.width == size.width() && image.height == size.height()) ||
        (size.width() == 0.0 || size.height() == 0.0)) {
      Future.successful(())
    } else {
      image.resize(size.width, size.height).map { img =>
        image := img
      }
    }
  }, automatic = false)

  override protected def init(): Unit = {
    super.init()

    updateMeasured(image.width, image.height)
    size.width.and(size.height).on(resizer.flag())
  }

  override def update(delta: Double): Unit = {
    super.update(delta)

    resizer.update()
  }

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)

  override protected def defaultThemeParent = Some(theme)

  override def `type`: String = "ImageView"

  override protected def modifiables: List[Modifiable] = super.modifiables ::: List(image())

  override protected def drawInternal(context: Context): Unit = {
    image.draw(context, 0.0, 0.0, size.width, size.height)
  }

  init()
}

object ImageView extends ImageViewTheme