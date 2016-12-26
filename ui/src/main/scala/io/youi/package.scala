package io

package object youi {
  implicit class NumericSize[T](t: T)(implicit n: Numeric[T]) {
    def px: Double = n.toDouble(t)
  }
}