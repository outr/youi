package io.youi.component.editor

import io.youi.component.extra.RectangularSelection
import io.youi.component.{AbstractContainer, Component, Image}
import reactify._

class ImageEditor extends AbstractContainer {
  override type Child = Component

  val maintainAspectRatio: Var[Boolean] = Var(true)

  val image: Image = new Image
  val rs: RectangularSelection = new RectangularSelection
  val pixelCount: Val[Double] = Val(image.texture.width * image.texture.height)

  image.position.center := size.center
  image.position.middle := size.middle

  rs.size.width := size.width
  rs.size.height := size.height
  rs.selection.aspectRatio := {
    if (maintainAspectRatio && image.texture.width() > 0.0 && image.texture.height() > 0.0) {
      Some(image.texture.width() / image.texture.height())
    } else {
      None
    }
  }

  size.width := image.size.width + rs.blocks.size
  size.height := image.size.height + rs.blocks.size

  childEntries ++= List(image, rs)

  pixelCount.on(rs.selection.maximize())
}
