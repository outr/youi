package io.youi.component

import io.youi.component.draw.{BoundingBox, Drawable}
import io.youi.image.Image
import org.scalajs.dom.File
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ImageView extends DrawableComponent {
  def this(file: File) = {
    this()
    load(file)
  }
  def this(path: String) = {
    this()
    load(path)
  }

  val image: Var[Image] = prop(Image.empty, updatesRendering = true)

  private object imageDrawer extends Drawable {
    override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
      image().drawImage(component, context, size.width(), size.height())
    }

    override def boundingBox: BoundingBox = image().boundingBox
  }

  drawable := imageDrawer

  def load(file: File): Future[Unit] = Image(file).map { image =>
    this.image := image
  }

  def load(path: String): Future[Unit] = Image(path).map { image =>
    this.image := image
  }
}

sealed trait ImageMode

object ImageMode {
  case object Quality extends ImageMode
  case object Speed extends ImageMode
}