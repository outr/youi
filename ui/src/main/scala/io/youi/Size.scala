package io.youi

sealed trait Size

case class DoubleSize(value: Double) extends Size {
  override def toString: String = s"${value}px"
}

object Size {
  def apply(value: Double): Size = DoubleSize(value)
  object Auto extends Size {
    override def toString: String = "auto"
  }
}