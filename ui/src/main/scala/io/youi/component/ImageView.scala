package io.youi.component

import io.youi._
import io.youi.component.draw.{BoundingBox, Drawable}
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.html
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.concurrent.ExecutionContext.Implicits.global

class ImageView extends DrawableComponent with Drawable {
  def this(image: Texture) = {
    this()
    this.image := image
  }

  val mode: Var[ImageMode] = Var(ImageMode.Quality)
  lazy val image: Var[Texture] = prop(new Texture(instance.texture))
  private var rendered: Option[html.Canvas] = None

  private val reRender = LazyUpdate {
    if (mode() == ImageMode.Quality && size.width() > 0.0 && size.height() > 0.0 && (size.width() != image.width() || size.height() != image.height())) {
      val r = CanvasPool(size.width(), size.height())
      val context = r.context
      context.clearRect(0.0, 0.0, r.width, r.height)
      ImageUtility.resizeToCanvas(image.source, r).foreach { _ =>
        rendered.foreach(CanvasPool.restore)
        rendered = Some(r)
        reDraw.flag()
      }
    } else {
      rendered.foreach(CanvasPool.restore)
      rendered = None
    }
  }

  image.on(reRender.flag())
  size.width.on(reRender.flag())
  size.height.on(reRender.flag())
  size.measured.width := image().width()
  size.measured.height := image().height()

  drawable := this

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = rendered match {
    case Some(r) => context.drawImage(r.asInstanceOf[html.Image], 0.0, 0.0, r.width, r.height)
    case None => if (image().width() > 0.0 && image().height() > 0.0) {
      context.drawImage(image().source.asInstanceOf[html.Image], 0.0, 0.0, size.width(), size.height())
    }
  }

  override def boundingBox: BoundingBox = BoundingBox.zero

  override def update(delta: Double): Unit = {
    super.update(delta)

    reRender.update()
  }
}

sealed trait ImageMode

object ImageMode {
  case object Quality extends ImageMode
  case object Speed extends ImageMode
}