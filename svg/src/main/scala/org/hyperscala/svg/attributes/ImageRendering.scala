package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class ImageRendering extends AttributeEntry[ImageRendering](parent = ImageRendering)

object ImageRendering extends AttributeObject[ImageRendering] {
  case object Auto extends ImageRendering
  case object OptimizeSpeed extends ImageRendering
  case object OptimizeQuality extends ImageRendering
  case object Inherit extends ImageRendering

  val values = findValues.toVector
}