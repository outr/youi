package io.youi.spatial

import reactify.Val

import scala.language.implicitConversions

case class Dim(value: Double, `type`: DimType = DimType.Manual) {
  def +(d: Double): Double = value + d
  def -(d: Double): Double = value - d
  def *(d: Double): Double = value * d
  def /(d: Double): Double = value / d
  def ceil: Double = value.ceil
}

object Dim {
  val Auto: Dim = Dim(0.0, DimType.Auto)

  implicit def dim2Double(dim: Dim): Double = dim.value
  implicit def double2Dim(value: Double): Dim = Dim(value)
  implicit def val2Dim(v: Val[Double]): Dim = Dim(v())
}