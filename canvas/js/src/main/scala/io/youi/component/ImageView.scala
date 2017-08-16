package io.youi.component

import io.youi.{Context, LazyFuture}
import io.youi.image.Image
import io.youi.theme.ImageViewTheme
import org.scalajs.dom.File
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ImageView extends Component {
  def this(file: File) = {
    this()
    load(file)
  }
  def this(source: String) = {
    this()
    load(source)
  }

  override lazy val theme: Var[ImageViewTheme] = Var(ImageView)

  val image: Var[Image] = prop(Image.empty, updatesRendering = true)

  override def draw(context: Context): Future[Unit] = super.draw(context).flatMap { _ =>
    image().drawImage(context)
  }

  private val resizer: LazyFuture[Unit] = LazyFuture {
    val original = image()
    if (original != Image.empty && size.width() > 0.0 && size.height() > 0.0 && (original.width != size.width() || original.height != size.height())) {
      original.resized(size.width, size.height).map { updated =>
        if (image() == original) { // Only update if the image hasn't been replaced
          if (updated.width == size.width() && updated.height == size.height()) {
            image := updated
            if (!updated.original.contains(original)) {
              original.dispose()
            }
          } else {
            resizer.flag()
          }
        }
      }
    } else {
      Future.successful(())
    }
  }

  size.measured.width := image.originalWidth
  size.measured.height := image.originalHeight

  size.width.and(size.height).on(resizer.flag())
  image.attach { i =>
    if (i != Image.empty && size.width() > 0.0 && size.height() > 0.0 && (i.width != size.width() || i.height != size.height())) {
      resizer.flag()
    }
  }

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