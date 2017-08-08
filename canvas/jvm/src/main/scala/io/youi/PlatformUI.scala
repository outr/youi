package io.youi

import reactify.Val

object PlatformUI extends UI {
  override lazy val ppi: Double = 72.0
  override lazy val width: Val[Double] = Val(1920.0)
  override lazy val height: Val[Double] = Val(1080.0)
}
