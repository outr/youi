package com.outr.webframework.js

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object Math {
  def random() = ExistingVariable[Double]("Math.random()")

  def floor(v: TypedVar[Double]) = ExistingVariable[Int]("Math.floor(%s)".format(v.reference.get))
}
