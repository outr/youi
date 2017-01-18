package io.youi

sealed trait Size

case class DoubleSize(value: Double) extends Size

object Size {
  def apply(value: Double): Size = DoubleSize(value)
  object Auto extends Size
}