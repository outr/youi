package io.youi.component

import io.youi.{Context, LazyFuture}
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import org.scalajs.dom.File
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ImageView extends Component with ImageViewTheme {
  def this(file: File) = {
    this()
    load(file)
  }
  def this(source: String) = {
    this()
    load(source)
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

  size.measured.width := image.width
  size.measured.height := image.height

  def load(file: File): Future[Image] = Image.fromFile(file).map { image =>
    this.image := image
    image
  }

  def load(source: String): Future[Image] = Image(source).map { image =>
    this.image := image
    image
  }
}

object ImageView extends ImageViewTheme