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
    source := image.source.asInstanceOf[html.Image].src
  }

  val mode: Var[ImageMode] = Var(ImageMode.Quality)
  lazy val source: Var[String] = prop("")
  val img: html.Image = dom.create[html.Image]("img")
  private var rendered: Option[html.Canvas] = None

  img.addEventListener("load", (evt: Event) => {
    size.measured.width := img.width.toDouble
    size.measured.height := img.height.toDouble
    reRender.flag()
  })
  source.attach(img.src = _)

  private var rendering = false

  private val reRender: LazyFuture[Unit] = LazyFuture {
    if (mode() == ImageMode.Quality
        && size.width() > 0.0 && size.height() > 0.0
        && img.width > 0 && img.height > 0
        && (size.width() != img.width || size.height() != img.height)) {
      val r = CanvasPool(size.width(), size.height())
      val context = r.context
      context.clearRect(0.0, 0.0, r.width, r.height)
      rendered.foreach(CanvasPool.restore)
      rendered = None
      rendering = true
      ImageUtility.resizeToCanvas(img, r).map { _ =>
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

  size.width.on(reRender.flag())
  size.height.on(reRender.flag())

  drawable := this

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = rendered match {
    case Some(r) => context.drawImage(r.asInstanceOf[html.Image], 0.0, 0.0, r.width, r.height)
    case None => if (img.width > 0.0 && img.height > 0.0 && size.width() > 0.0 && size.height() > 0.0) {
      context.drawImage(img, 0.0, 0.0, size.width(), size.height())
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