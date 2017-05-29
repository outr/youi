package io.youi.component.filter

import org.scalajs.dom.raw.ImageData

trait CanvasFilter {
  def apply(imageData: ImageData): Unit
}