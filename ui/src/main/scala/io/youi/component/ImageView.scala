package io.youi.component

import io.youi.LazyFuture
import io.youi.component.draw.{BoundingBox, Drawable}
import io.youi.image.Image
import org.scalajs.dom.File
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ImageView extends DrawableComponent {
  def this(file: File) = {
    this()
    load(file)
  }
  def this(path: String) = {
    this()
    load(path)
  }

  val image: Var[Image] = prop(Image.empty, _ => reDraw.flag())

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

  size.width.and(size.height).on(resizer.flag())
  image.attach { i =>
    if (i != Image.empty && size.width() > 0.0 && size.height() > 0.0 && (i.width != size.width() || i.height != size.height())) {
      resizer.flag()
    }
  }

  private object imageDrawer extends Drawable {
    override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
      image().drawImage(component, context, size.width(), size.height())
    }

    override def boundingBox: BoundingBox = image().boundingBox
  }

  drawable := imageDrawer

  def load(file: File): Future[Unit] = Image.fromFile(file).map { image =>
    scribe.info(s"Loaded file ${file.name} into image: ${image.width}x${image.height}")
    this.image := image
  }

  def load(source: String): Future[Unit] = Image(source).map { image =>
    this.image := image
  }
}

sealed trait ImageMode

object ImageMode {
  case object Quality extends ImageMode
  case object Speed extends ImageMode
}