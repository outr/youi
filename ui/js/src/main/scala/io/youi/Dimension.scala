package io.youi

sealed trait Dimension {
  def isNumeric: Boolean
  def value: Double
}

class DoubleDimension(override val value: Double) extends AnyVal with Dimension {
  override def isNumeric: Boolean = true
}

object Dimension {
  def apply(value: Double): Dimension = new DoubleDimension(value)
  case object Auto extends Dimension {
    override def isNumeric: Boolean = false
    override def value: Double = 0.0
  }
}