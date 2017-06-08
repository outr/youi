package io.youi.component.editor

import io.youi.Compass
import io.youi.component.extra.RectangularSelection
import io.youi.component.{AbstractContainer, Component, Image}
import reactify._

class ImageEditor extends AbstractContainer {
  override type Child = Component

  val aspectRatio: Var[AspectRatio] = Var(AspectRatio.Original)

  val image: Image = new Image
  val rs: RectangularSelection = new RectangularSelection
  val pixelCount: Val[Double] = Val(image.texture.width * image.texture.height)

  image.scale.direction := Compass.Center
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

  size.width := image.size.width + rs.blocks.size
  size.height := image.size.height + rs.blocks.size

  childEntries ++= List(image, rs)

  pixelCount.on(reset())

  def reset(): Unit = {
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