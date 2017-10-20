package io.youi.component.editor

import io.youi._
import io.youi.component._
import io.youi.component.extra.RectangularSelection
import io.youi.image.Image
import io.youi.image.resize.ImageResizer
import io.youi.model.{ImageEditorInfo, ImageInfo, SelectionInfo}
import io.youi.spatial.{Point, Size}
import io.youi.util.{CanvasPool, LazyFuture, SizeUtility}
import org.scalajs.dom.{File, html}
import reactify._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

class ImageEditor extends AbstractContainer {
  override type Child = Component

  override def `type`: String = "ImageEditor"

  val aspectRatio: Var[AspectRatio] = Var(AspectRatio.Original)
  val imageScale: Var[Double] = Var(1.0)
  val autoFit: Var[Boolean] = Var(false)
  val minPreviewSize: Var[Size] = Var(Size(1024.0, 1024.0))
  val maxOriginalSize: Var[Size] = Var(Size(10000.0, 10000.0))

  private val originalImage: Var[Image] = Var(Image.empty)
  val imageView: ImageView = new ImageView
  val rs: RectangularSelection = new RectangularSelection
  val pixelCount: Val[Double] = Val(imageView.size.measured.width * imageView.size.measured.height)
  val wheelMultiplier: Var[Double] = Var(0.001)
  val revision: Val[Int] = Var(0)

//  val preview: Var[html.Canvas] = Var(CanvasPool(rs.selection.width, rs.selection.height), static = true)

  /*private val previewUpdater = LazyFuture({
    val min = minPreviewSize()
    val scaled = SizeUtility.scale(rs.selection.width(), rs.selection.height(), min.width, min.height, scaleUp = true)
    val width = scaled.width
    val height = scaled.height
    val scale = scaled.scale
    val destination = CanvasPool(width, height)
    val canvasContext = destination.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
    canvasContext.scale(scale, scale)
    canvasContext.translate(imageView.position.x - rs.selection.x1, imageView.position.y - rs.selection.y1)
    canvasContext.translate(imageView.size.width / 2.0, imageView.size.height / 2.0)
    canvasContext.rotate(imageView.rotation() * (math.Pi * 2.0))
    canvasContext.translate(-imageView.size.width / 2.0, -imageView.size.height / 2.0)
    val context = new Context(destination, ui.ratio)

    originalImage.drawAsync(context, 0.0, 0.0, imageView.size.width, imageView.size.height).map { _ =>
      val previous = preview()
      preview := destination
      CanvasPool.restore(previous)
    }
  }, automatic = false)*/

  def preview(width: Double, height: Double, resizer: ImageResizer): html.Canvas = {
    val canvas = CanvasPool(width, height)
    val lf = LazyFuture {
      imageView.image().resizeTo(canvas, width, height, resizer)
    }
    imageView.image.on(lf.flag())
    revision.on(lf.flag())
    lf.flag()
    canvas
  }

  def preview(img: html.Image, width: Double, height: Double, resizer: ImageResizer): Unit = {
    val canvas = CanvasPool(width, height)
    val context = canvas.context
    val lf = LazyFuture({
      val min = minPreviewSize()
      val scaled = SizeUtility.scale(rs.selection.width(), rs.selection.height(), min.width, min.height, scaleUp = true)
      val width = scaled.width
      val height = scaled.height
      val scale = scaled.scale
      canvas.width = math.ceil(width).toInt
      canvas.height = math.ceil(height).toInt

      context.clearRect(0.0, 0.0, canvas.width, canvas.height)
      context.scale(scale, scale)
      context.translate(imageView.position.x - rs.selection.x1, imageView.position.y - rs.selection.y1)
      context.translate(imageView.size.width / 2.0, imageView.size.height / 2.0)
      context.rotate(imageView.rotation() * (math.Pi * 2.0))
      context.translate(-imageView.size.width / 2.0, -imageView.size.height / 2.0)
      imageView.image().resizeTo(canvas, width, height, resizer).map { _ =>
        img.src = canvas.toDataURL("image/png")
      }
    }, maxFrequency = 250.millis, automatic = false)
    delta.on(lf.update())
    imageView.image.on(lf.flag())
    revision.on(lf.flag())
    lf.flag()
  }

  override protected def init(): Unit = {
    super.init()

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

    size.width := imageView.size.width + rs.blocks.size
    size.height := imageView.size.height + rs.blocks.size

    childEntries ++= List(imageView, rs)

    rs.event.pointer.wheel.attach { evt =>
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
  }

  def load(file: File): Future[Image] = Image(file).flatMap(load)

  def load(path: String): Future[Image] = Image(path).flatMap(load)

  def load(img: Image): Future[Image] = {
    if (img.width > maxOriginalSize.width || img.height > maxOriginalSize.height) {
      val scale = SizeUtility.scale(img.width, img.height, maxOriginalSize.width, maxOriginalSize.height)
      img.resize(scale.width, scale.height)
    } else {
      imageView.image := img
      originalImage := img
      reset()
      Future.successful(img)
    }
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

  def originalDataURL: Future[String] = originalImage.toDataURL

  init()
}

sealed trait AspectRatio

object AspectRatio {
  case class Defined(value: Double) extends AspectRatio
  case object None extends AspectRatio
  case object Original extends AspectRatio

  def fromSize(width: Double, height: Double): AspectRatio = Defined(width / height)
}