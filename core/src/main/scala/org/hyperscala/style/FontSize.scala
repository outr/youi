package com.outr.webframework.style

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object FontSize {
  val XXSmall = Length("xx-small")
  val XSmall = Length("x-small")
  val Small = Length("small")
  val Medium = Length("medium")
  val Large = Length("large")
  val XLarge = Length("x-large")
  val XXLarge = Length("xx-large")
  val Smaller = Length("smaller")
  val Larger = Length("larger")
  def Inherit = Length.Inherit
  def Pixels(v: Int) = Length.Pixels(v)
  def Centimeters(v: Int) = Length.Centimeters(v)
  def Percent(v: Int) = Length.Percent(v)
  def Points(v: Int) = Length("%spt".format(v))
}
