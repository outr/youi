package io.youi.component.editor

import io.youi._
import io.youi.component.extra.RectangularSelection
import io.youi.component._
import io.youi.util.{CanvasPool, ImageUtility, SizeUtility}
import org.scalajs.dom.{File, html}
import reactify._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class ImageEditor extends AbstractContainer {
  override type Child = Component

  val aspectRatio: Var[AspectRatio] = Var(AspectRatio.Original)

  val imageView: ImageView = new ImageView
  val rs: RectangularSelection = new RectangularSelection
  val pixelCount: Val[Double] = Val(imageView.image.width * imageView.image.height)
  val wheelMultiplier: Var[Double] = Var(0.001)
  val revision: Val[Int] = Var(0)

  imageView.position.center := size.center
  imageView.position.middle := size.middle

  rs.size.width := size.width
  rs.size.height := size.height
  rs.selection.aspectRatio := {
    aspectRatio() match {
      case AspectRatio.Defined(value) => Some(value)
      case AspectRatio.None => None
      case AspectRatio.Original => if (imageView.image.width() > 0.0 && imageView.image.height() > 0.0) {
        Some(imageView.image.width() / imageView.image.height())
      } else {
        None
      }
    }
  }

  val preview: Var[html.Canvas] = Var(CanvasPool(rs.selection.width, rs.selection.height), static = true)

  private val previewUpdater = LazyUpdate({
    val destination = CanvasPool(rs.selection.width, rs.selection.height)
    try {
      val context = destination.context
      context.translate(imageView.position.x - rs.selection.x1, imageView.position.y - rs.selection.y1)
      context.translate(imageView.size.width / 2.0, imageView.size.height / 2.0)
      context.rotate(imageView.rotation() * (math.Pi * 2.0))
      context.translate(-imageView.size.width / 2.0, -imageView.size.height / 2.0)
      context.drawImage(imageView.image.source.asInstanceOf[html.Image], 0.0, 0.0, imageView.size.width, imageView.size.height)
      val previous = preview()
      preview := destination
      CanvasPool.restore(previous)
    } catch {
      case t: Throwable => {
        scribe.error(t)
        CanvasPool.restore(destination)
      }
    }
  }, 100.millis)

  revision.on(previewUpdater.flag())
  delta.on(previewUpdater.update())

  def previewImage(width: Double, height: Double): hypertext.ImageView = {
    val view = new hypertext.ImageView
    view.size.width := width
    view.size.height := height
    previewImage(view)
    view
  }

  def previewImage(view: hypertext.ImageView): Unit = {
    preview.attachAndFire { canvas =>
      ImageUtility.resizeToImage(canvas, view.size.width, view.size.height, view.element)
    }
  }

  def previewImage(img: html.Image, width: Double, height: Double): Unit = {
    preview.attachAndFire { canvas =>
      ImageUtility.resizeToImage(canvas, width, height, img)
    }
  }

  size.width := imageView.size.width + rs.blocks.size
  size.height := imageView.size.height + rs.blocks.size

  childEntries ++= List(imageView, rs)

  pixelCount.on(reset())

  event.pointer.wheel.attach { evt =>
    scale(evt.delta.y * -wheelMultiplier, Some(evt.local))
  }
  event.gestures.pointers.dragged.attach { p =>
    if (event.gestures.pointers.map.size == 1 && !rs.isDragging) {
      imageView.position.x.static(imageView.position.x() + p.moved.deltaX)
      imageView.position.y.static(imageView.position.y() + p.moved.deltaY)
    }
  }
  event.gestures.pinch.attach { evt =>
    scale(evt.deltaDistance * 0.01, Some(evt.pointer.move.local))
  }

  Observable.wrap(
    imageView.position.x, imageView.position.y, imageView.rotation, imageView.size.width, imageView.size.height,
    rs.selection.x1, rs.selection.y1, rs.selection.x2, rs.selection.y2
  ).on {
    val current = revision()
    revision.asInstanceOf[Var[Int]] := current + 1
  }

  def load(file: File): Unit = {
    ImageUtility.loadImage(file).foreach { image =>
      this.imageView.image := Texture(image)
    }
  }

  def load(path: String): Unit = {
    imageView.image := Texture(path)
  }

  def scale(amount: Double, point: Option[Point] = None): Unit = {
    val newScale = math.max(imageView.scale.x() + amount, 0.1)
    imageView.size.width := imageView.size.measured.width * newScale
    imageView.size.height := imageView.size.measured.height * newScale
    point.foreach { p =>
      val offsetX = p.x - size.center
      val offsetY = p.y - size.middle
      val center = imageView.position.center() - (offsetX * amount)
      val middle = imageView.position.middle() - (offsetY * amount)
      imageView.position.center := center
      imageView.position.middle := middle
    }
  }

  def rotate(amount: Double): Unit = {
    val current = imageView.rotation()
    imageView.rotation := current + amount
  }

  def reset(): Unit = {
    imageView.position.center := size.center
    imageView.position.middle := size.middle

    val scaled = SizeUtility.scale(imageView.size.measured.width, imageView.size.measured.height, size.width - (rs.blocks.size() * 2.0), size.height - (rs.blocks.size() * 2.0))
    imageView.size.width := scaled.x
    imageView.size.height := scaled.y

    imageView.rotation := 0.0

    val x1 = math.max(imageView.position.left(), rs.selection.minX)
    val y1 = math.max(imageView.position.top(), rs.selection.minY)
    val x2 = math.min(imageView.position.right(), rs.selection.maxX)
    val y2 = math.min(imageView.position.bottom(), rs.selection.maxY)
    rs.selection.set(x1, y1, x2, y2)
  }
}

sealed trait AspectRatio

object AspectRatio {
  case class Defined(value: Double) extends AspectRatio
  case object None extends AspectRatio
  case object Original extends AspectRatio

  def fromSize(width: Double, height: Double): AspectRatio = Defined(width / height)
}