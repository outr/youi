package io.youi

import reactify._

trait UI {
  def ppi: Double
  def width: Val[Double]
  def height: Val[Double]

  object position {
    val x: Val[Double] = Val(0.0)
    val y: Val[Double] = Val(0.0)

    val left: Val[Double] = x
    val center: Val[Double] = Val(width / 2.0)
    val right: Val[Double] = width

    val top: Val[Double] = y
    val middle: Val[Double] = Val(height / 2.0)
    val bottom: Val[Double] = height
  }
}