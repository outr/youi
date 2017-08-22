package io.youi.filter

import io.youi.Pixel

object GrayScale extends PerPixelCanvasFilter {
  override def apply(pixel: Pixel): Unit = {
    val brightness = (3 * pixel.red + 4 * pixel.green + pixel.blue) >>> 3
    pixel.red = brightness
    pixel.green = brightness
    pixel.blue = brightness
  }
}