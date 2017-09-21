package io.youi

import reactify._

trait Widget {
  def position: WidgetPosition
  def size: WidgetSize
}

trait WidgetPosition {
  def x: Var[Double]
  def y: Var[Double]

  def left: Var[Double]
  def center: Dep[Double, Double]
  def right: Dep[Double, Double]

  def top: Var[Double]
  def middle: Dep[Double, Double]
  def bottom: Dep[Double, Double]
}

trait WidgetSize {
  def width: Var[Double]
  def height: Var[Double]
}