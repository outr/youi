package io.youi.spatial

class YouiMathException(cause: String) extends Exception {
  override def toString: String = cause
}