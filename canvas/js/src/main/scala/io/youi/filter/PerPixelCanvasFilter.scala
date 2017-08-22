package io.youi.filter

import io.youi.Pixel
import org.scalajs.dom.raw.ImageData

trait PerPixelCanvasFilter extends CanvasFilter {
  override def apply(imageData: ImageData): Unit = {
    val data = imageData.data
    val pixel = new Pixel()
    (0 until data.length by 4).foreach { index =>
      pixel.red = data(index)
      pixel.green = data(index + 1)
      pixel.blue = data(index + 2)
      pixel.alpha = data(index + 3)
      apply(pixel)
      data(index) = pixel.red
      data(index + 1) = pixel.green
      data(index + 2) = pixel.blue
      data(index + 3) = pixel.alpha
    }
  }

  def apply(pixel: Pixel): Unit
}