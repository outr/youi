package io.youi.component

import io.youi.LazyFuture
import io.youi.component.draw.{BoundingBox, Drawable}
import io.youi.image.Image
import org.scalajs.dom.{File, html}
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ImageView extends DrawableComponent {
  def this(file: File) = {
    this()
    load(file)
  }
  def this(source: String) = {
    this()
    load(source)
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

  size.measured.width := image.originalWidth
  size.measured.height := image.originalHeight

  size.width.and(size.height).on(resizer.flag())
  image.attach { i =>
    if (i != Image.empty && size.width() > 0.0 && size.height() > 0.0 && (i.width != size.width() || i.height != size.height())) {
      resizer.flag()
    }
  }

  drawable := new ImageDrawer(image, canvas)

  def load(file: File): Future[Image] = Image.fromFile(file).map { image =>
    this.image := image
    image
  }

  def load(source: String): Future[Image] = Image(source).map { image =>
    this.image := image
    image
  }
}

sealed trait ImageMode

object ImageMode {
  case object Quality extends ImageMode
  case object Speed extends ImageMode
}

class ImageDrawer(image: Image, canvas: html.Canvas) extends Drawable {
  override def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit] = {
    image.drawImage(component, canvas, context, component.size.width(), component.size.height())
  }

  override def boundingBox: BoundingBox = image.boundingBox
}