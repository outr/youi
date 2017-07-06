package io.youi.component.editor

import io.youi._
import io.youi.component.extra.RectangularSelection
import io.youi.component._
import io.youi.image.Image
import io.youi.util.{CanvasPool, ImageUtility, SizeUtility}
import org.scalajs.dom.{File, html}
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ImageEditor extends AbstractContainer {
  override type Child = Component

  val aspectRatio: Var[AspectRatio] = Var(AspectRatio.Original)
  val imageScale: Var[Double] = Var(1.0)
  val autoFit: Var[Boolean] = Var(false)
  val minPreviewSize: Var[Size] = Var(Size(1024.0, 1024.0))

  private val originalImage: Var[Image] = Var(Image.empty)
  private val imageView: ImageView = new ImageView
  val rs: RectangularSelection = new RectangularSelection
  val pixelCount: Val[Double] = Val(imageView.size.measured.width * imageView.size.measured.height)
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
      case AspectRatio.Original => if (imageView.size.measured.width() > 0.0 && imageView.size.measured.height() > 0.0) {
        Some(imageView.size.measured.width() / imageView.size.measured.height())
      } else {
        None
      }
    }
  }

  val preview: Var[html.Canvas] = Var(CanvasPool(rs.selection.width, rs.selection.height), static = true)

  private val previewUpdater = LazyFuture({
    val min = minPreviewSize()
    val scaled = SizeUtility.scale(rs.selection.width(), rs.selection.height(), min.width, min.height, scaleUp = true)
    val width = scaled.width
    val height = scaled.height
    val scale = scaled.scale
    val destination = CanvasPool(width, height)
    val context = destination.context
    context.scale(scale, scale)
    context.translate(imageView.position.x - rs.selection.x1, imageView.position.y - rs.selection.y1)
    context.translate(imageView.size.width / 2.0, imageView.size.height / 2.0)
    context.rotate(imageView.rotation() * (math.Pi * 2.0))
    context.translate(-imageView.size.width / 2.0, -imageView.size.height / 2.0)
    originalImage.drawImage(this, destination, context, imageView.size.width, imageView.size.height).map { _ =>
      val previous = preview()
      preview := destination
      CanvasPool.restore(previous)
    }
  }, automatic = false)

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
    val resizer = LazyFuture {
      ImageUtility.resizeToImage(preview(), view.size.width, view.size.height, view.element)
    }
    preview.attachAndFire { canvas =>
      if (canvas.width > 0 && canvas.height > 0 && view.size.width() > 0.0 && view.size.height() > 0.0) {
        resizer.flag()
      }
    }
  }

  def previewImage(img: html.Image, width: Double, height: Double): Unit = {
    val resizer = LazyFuture {
      if (preview().width > 0 && preview().height > 0 && width > 0.0 && height > 0.0) {
        ImageUtility.resizeToImage(preview(), width, height, img)
      } else {
        Future.successful(img)
      }
    }
    preview.attachAndFire { _ =>
      resizer.flag()
    }
  }

  size.width := imageView.size.width + rs.blocks.size
  size.height := imageView.size.height + rs.blocks.size

  childEntries ++= List(imageView, rs)

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

  def load(file: File): Future[Image] = imageView.load(file).map { img =>
    originalImage := img
    reset()
    img
  }

  def load(path: String): Future[Image] = imageView.load(path).map { img =>
    originalImage := img
    reset()
    img
  }

  def info: ImageEditorInfo = ImageEditorInfo(
    ImageInfo(imageView.position.center(), imageView.position.middle(), imageView.rotation(), imageScale()),
    SelectionInfo(rs.selection.x1(), rs.selection.y1(), rs.selection.x2(), rs.selection.y2())
  )

  def info_=(info: ImageEditorInfo): Unit = {
    imageView.position.center := info.image.center
    imageView.position.middle := info.image.middle
    imageView.rotation := info.image.rotation
    imageScale.static(info.image.scale)
    updateFromScale()
    rs.selection.x1 := info.selection.x1
    rs.selection.y1 := info.selection.y1
    rs.selection.x2 := info.selection.x2
    rs.selection.y2 := info.selection.y2
  }

  def scale(amount: Double, point: Option[Point] = None): Unit = {
    imageScale.static(math.max(imageScale + amount, 0.1))
    updateFromScale()
    point.foreach { p =>
      val offsetX = p.x - size.center
      val offsetY = p.y - size.middle
      val center = imageView.position.center() - (offsetX * amount)
      val middle = imageView.position.middle() - (offsetY * amount)
      imageView.position.center := center
      imageView.position.middle := middle
    }
  }

  private def updateFromScale(): Unit = {
    imageView.size.width.static(imageView.size.measured.width * imageScale)
    imageView.size.height.static(imageView.size.measured.height * imageScale)
  }

  def rotate(amount: Double): Unit = {
    val current = imageView.rotation()
    imageView.rotation := current + amount
  }

  def reset(): Unit = {
    imageView.rotation := 0.0
    if (autoFit()) {
      fit()
    } else {
      original()
    }
  }

  def reCenter(): Unit = {
    imageView.position.center := size.center
    imageView.position.middle := size.middle
  }

  def fit(): Unit = {
    reCenter()
    val r = math.abs(imageView.rotation() % 1.0)
    val flipped = r == 0.25 || r == 0.75
    val cw = size.width - (rs.blocks.size() * 2.0)
    val ch = size.height - (rs.blocks.size() * 2.0)
    val scaled = SizeUtility.scale(imageView.size.measured.width, imageView.size.measured.height, if (flipped) ch else cw, if (flipped) cw else ch, scaleUp = imageView.image.isVector)
    imageView.size.width := scaled.width
    imageView.size.height := scaled.height
    imageScale := scaled.scale
    resetSelection()
  }

  def original(): Unit = {
    reCenter()
    imageView.size.width := imageView.size.measured.width
    imageView.size.height := imageView.size.measured.height
    imageScale := 1.0
    resetSelection()
  }

  def resetSelection(): Unit = {
    val r = math.abs(imageView.rotation() % 1.0)
    val flipped = r == 0.25 || r == 0.75

    val left = if (flipped) imageView.position.center() - (imageView.size.height() / 2.0) else imageView.position.left()
    val top = if (flipped) imageView.position.middle() - (imageView.size.width() / 2.0) else imageView.position.top()
    val right = if (flipped) imageView.position.center() + (imageView.size.height() / 2.0) else imageView.position.right()
    val bottom = if (flipped) imageView.position.middle() + (imageView.size.width() / 2.0) else imageView.position.bottom()

    val x1 = math.max(left, rs.selection.minX)
    val y1 = math.max(top, rs.selection.minY)
    val x2 = math.min(right, rs.selection.maxX)
    val y2 = math.min(bottom, rs.selection.maxY)
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

case class ImageEditorInfo(image: ImageInfo, selection: SelectionInfo)

case class ImageInfo(center: Double, middle: Double, rotation: Double, scale: Double)

case class SelectionInfo(x1: Double, y1: Double, x2: Double, y2: Double)