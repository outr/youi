package io.youi.component.editor

import io.youi._
import io.youi.component.extra.RectangularSelection
import io.youi.component.{AbstractContainer, Component, Image}
import io.youi.hypertext.ImageView
import io.youi.util.{CanvasPool, ImageUtility}
import org.scalajs.dom.html
import reactify._

import scala.concurrent.duration._

class ImageEditor extends AbstractContainer {
  override type Child = Component

  val aspectRatio: Var[AspectRatio] = Var(AspectRatio.Original)

  val image: Image = new Image
  val rs: RectangularSelection = new RectangularSelection
  val pixelCount: Val[Double] = Val(image.texture.width * image.texture.height)
  val wheelMultiplier: Var[Double] = Var(0.001)
  val revision: Val[Int] = Var(0)

//  image.scale.direction := Compass.Center
  image.position.center := size.center
  image.position.middle := size.middle

  rs.size.width := size.width
  rs.size.height := size.height
  rs.selection.aspectRatio := {
    aspectRatio() match {
      case AspectRatio.Defined(value) => Some(value)
      case AspectRatio.None => None
      case AspectRatio.Original => if (image.texture.width() > 0.0 && image.texture.height() > 0.0) {
        Some(image.texture.width() / image.texture.height())
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
      context.translate(image.position.x - rs.selection.x1, image.position.y - rs.selection.y1)

      context.drawImage(image.texture.source.asInstanceOf[html.Image], 0.0, 0.0, image.size.width, image.size.height)
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

  def previewImage(width: Double, height: Double): ImageView = {
    val view = new ImageView
    view.size.width := width
    view.size.height := height
    previewImage(view)
    view
  }

  def previewImage(view: ImageView): Unit = {
    preview.attachAndFire { canvas =>
      ImageUtility.resizeToImage(canvas, view.size.width, view.size.height, view.element)
    }
  }

  size.width := image.size.width + rs.blocks.size
  size.height := image.size.height + rs.blocks.size

  childEntries ++= List(image, rs)

  pixelCount.on(reset())

  event.pointer.wheel.attach { evt =>
    scale(evt.delta.y * -wheelMultiplier, Some(evt.local))
  }
  event.gestures.pointers.dragged.attach { p =>
    if (event.gestures.pointers.map.size == 1 && !rs.isDragging) {
      image.position.x := image.position.x() + p.moved.deltaX
      image.position.y := image.position.y() + p.moved.deltaY
    }
  }

  Observable.wrap(
    image.position.x, image.position.y, image.scale.x, image.scale.y,
    rs.selection.x1, rs.selection.y1, rs.selection.x2, rs.selection.y2
  ).on {
    val current = revision()
    revision.asInstanceOf[Var[Int]] := current + 1
  }

  def scale(amount: Double, point: Option[Point] = None): Unit = {
    val newScale = math.max(image.scale.x() + amount, 0.1)
    image.size.width := image.size.measured.width * newScale
    image.size.height := image.size.measured.height * newScale
    point.foreach { p =>
      val offsetX = p.x - size.center
      val offsetY = p.y - size.middle
      val center = image.position.center() - (offsetX * amount)
      val middle = image.position.middle() - (offsetY * amount)
      image.position.center := center
      image.position.middle := middle
    }
  }

  def rotate(amount: Double): Unit = {
    val current = image.rotation()
    image.rotation := current + amount
  }

  def reset(): Unit = {
    image.position.center := size.center
    image.position.middle := size.middle
    image.size.reset()
    image.rotation := 0.0

    val x1 = math.max(image.position.left(), rs.selection.minX)
    val y1 = math.max(image.position.top(), rs.selection.minY)
    val x2 = math.min(image.position.right(), rs.selection.maxX)
    val y2 = math.min(image.position.bottom(), rs.selection.maxY)
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