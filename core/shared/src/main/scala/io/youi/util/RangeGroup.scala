package io.youi.util

case class RangeGroup(calc: () => Int, ranges: Range*) {
  def apply[T](values: T*): T = {
    assert(values.length == ranges.length, s"Expected ${ranges.length}, got ${values.length}")
    val v = calc()
    val range = ranges.find(_.contains(v)).getOrElse(throw new RuntimeException(s"Unable to find $v in range group"))
    val index = ranges.indexOf(range)
    values(index)
  }
}