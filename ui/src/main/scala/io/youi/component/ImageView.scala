package io.youi.component

import io.youi._
import io.youi.component.draw.{BoundingBox, Drawable}
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom._
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.{Val, Var}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ImageView extends DrawableComponent with Drawable {
  def this(image: Texture) = {
    this()
    this.image := image
  }

  val mode: Var[ImageMode] = Var(ImageMode.Quality)
  lazy val image: Var[Texture] = prop(new Texture(instance.texture))
  lazy val imageUpdate: Val[Long] = Val(image.update)
  private var rendered: Option[html.Canvas] = None

  private var rendering = false

  private val reRender: LazyFuture[Unit] = LazyFuture {
    if (mode() == ImageMode.Quality && size.width() > 0.0 && size.height() > 0.0 && (size.width() != image.width() || size.height() != image.height())) {
      val r = CanvasPool(size.width(), size.height())
      val context = r.context
      context.clearRect(0.0, 0.0, r.width, r.height)
      rendered.foreach(CanvasPool.restore)
      rendered = None
      rendering = true
      ImageUtility.resizeToCanvas(image.source, r).map { _ =>
        if (!reRender.isFlagged) {      // Don't draw dirty
          rendered = Some(r)
          reDraw.flag()
          rendering = false
        }
      }
    } else {
      rendered.foreach(CanvasPool.restore)
      rendered = None
      reDraw.flag()
      Future.successful(())
    }
  }

  image.on(reRender.flag())
  size.width.on(reRender.flag())
  size.height.on(reRender.flag())
  imageUpdate.on(reRender.flag())
  size.measured.width := image().width()
  size.measured.height := image().height()

  drawable := this

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = rendered match {
    case Some(r) => context.drawImage(r.asInstanceOf[html.Image], 0.0, 0.0, r.width, r.height)
    case None => if (image().width() > 0.0 && image().height() > 0.0 && size.width() > 0.0 && size.height() > 0.0 && image.valid) {
      scribe.info(s"Drawing image (${id()})... Valid? ${image().instance.valid}")
      context.drawImage(image().source.asInstanceOf[html.Image], 0.0, 0.0, size.width(), size.height())
    } else {
      scribe.info("Ignoring for invalid image!")
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