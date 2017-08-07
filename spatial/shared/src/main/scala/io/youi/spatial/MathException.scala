package io.youi.spatial

class MathException(cause: String) extends Exception {
  override def toString: String = cause
}