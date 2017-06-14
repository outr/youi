package io.youi.util

import io.youi.Point

object SizeUtility {
  def scale(width: Double,
            height: Double,
            insideWidth: Double,
            insideHeight: Double,
            scaleUp: Boolean = false): Point = {
    if (scaleUp || width > insideWidth || height > insideHeight) {
      val wa = insideWidth / width
      val ha = insideHeight / height
      if (wa < ha) {
        Point(insideWidth, height * wa)
      } else {
        Point(width * ha, insideHeight)
      }
    } else {
      Point(width, height)
    }
  }
}
