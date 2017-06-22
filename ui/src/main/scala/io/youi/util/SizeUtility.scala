package io.youi.util

import io.youi.Point

object SizeUtility {
  def scale(width: Double,
            height: Double,
            insideWidth: Double,
            insideHeight: Double,
            scaleUp: Boolean = false): Scaled = {
    if (scaleUp || width > insideWidth || height > insideHeight) {
      val wa = insideWidth / width
      val ha = insideHeight / height
      if (wa < ha) {
        Scaled(insideWidth, height * wa, wa)
      } else {
        Scaled(width * ha, insideHeight, ha)
      }
    } else {
      Scaled(width, height, 1.0)
    }
  }
}

case class Scaled(width: Double, height: Double, scale: Double)