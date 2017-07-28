package io.youi.math

class YouiMathException(cause: String) extends Exception {
  override def toString: String = cause
}