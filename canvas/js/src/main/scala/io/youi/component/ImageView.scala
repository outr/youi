package io.youi.component

import io.youi.{Context, ImageMode}
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import org.scalajs.dom.File
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ImageView extends Component with ImageViewTheme {
  def this(image: Image) = {
    this()
    this.image := image
  }
  def this(file: File, mode: ImageMode) = {
    this()
    load(file, mode)
  }
  def this(source: String, mode: ImageMode) = {
    this()
    load(source, mode)
  }

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)

  override protected def defaultThemeParent = Some(theme)

  val image: Var[Image] = prop(Image.empty, updatesRendering = true)

  override def draw(context: Context): Unit = {
    super.draw(context)

    if (size.width() > 0.0 && size.height() > 0.0) {
      val asyncRedraw = image().drawFast(context, size.width(), size.height())
      if (asyncRedraw) {
        reDrawAsync(image().draw(_, size.width(), size.height()))
      }
    }
  }

  updateMeasured(image.width, image.height)

  def load(file: File, mode: ImageMode): Future[Image] = Image.fromFile(file, mode = mode).map { image =>
    this.image := image
    image
  }

  def load(source: String, mode: ImageMode): Future[Image] = Image(source, mode = mode).map { image =>
    this.image := image
    image
  }
}

object ImageView extends ImageViewTheme