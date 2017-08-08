package io.youi

import io.youi.drawable.Drawable
import reactify.Val

trait UI {
  def ppi: Double
  def width: Val[Double]
  def height: Val[Double]
  def createDrawable(): Drawable
}