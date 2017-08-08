package io.youi

import io.youi.drawable.Drawable
import reactify.Val

object PlatformUI extends UI {
  override lazy val ppi: Double = 72.0
  override lazy val width: Val[Double] = Val(1920.0)
  override lazy val height: Val[Double] = Val(1080.0)
  override def createDrawable(): Drawable = NoopDrawable
}
