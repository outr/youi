package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class ImageRendering extends AttributeEntry[ImageRendering](parent = ImageRendering)

object ImageRendering extends AttributeObject[ImageRendering] {
  val Auto = new ImageRendering
  val OptimizeSpeed = new ImageRendering
  val OptimizeQuality = new ImageRendering
  val Inherit = new ImageRendering
}