package io.youi.util

import io.youi.spatial.Size

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

  def size(width: Option[Double], height: Option[Double], original: Size): Size = width match {
    case Some(w) => height match {
      case Some(h) => {
        Size(w, h)
      }
      case None => {
        val aspectRatio = original.height / original.width
        Size(w, w * aspectRatio)
      }
    }
    case None => {
      height match {
        case Some(h) => {
          val aspectRatio = original.width / original.height
          Size(h * aspectRatio, h)
        }
        case None => Size(original.width, original.height)
      }
    }
  }
}

case class Scaled(width: Double, height: Double, scale: Double) {
  override def toString: String = s"Scaled(width: $width, height: $height, scale: $scale)"
}