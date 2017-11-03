package io.youi.component

import io.youi.Modifiable
import io.youi.drawable.Context
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import io.youi.util.LazyFuture
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ImageView extends Component with ImageViewTheme {
  def this(source: String) = {
    this()

    Image(source).foreach(image := _)
  }

  def this(image: Image) = {
    this()

    this.image := image
  }

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
      resizeAsynchronously()
    }
  }, automatic = false)

  private def resizeAsynchronously(): Future[Unit] = {
    val w = size.width()
    val h = size.height()
    image.resize(w, h).map { img =>
      image := img
      if (w != size.width() || h == size.height()) {
        resizer.flag()
      }
    }
  }

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