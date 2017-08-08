package io.youi

import reactify.Val

trait UI {
  def ppi: Double
  def width: Val[Double]
  def height: Val[Double]
}