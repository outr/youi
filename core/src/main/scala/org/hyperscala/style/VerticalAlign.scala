package org.hyperscala.style

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
object VerticalAlign {
  val Baseline = Length("baseline")
  val Sub = Length("sub")
  val Super = Length("super")
  val Top = Length("top")
  val TextTop = Length("text-top")
  val Middle = Length("middle")
  val Bottom = Length("bottom")
  val TextBottom = Length("text-bottom")
  def Inherit = Length.Inherit
  def Pixels(v: Int) = Length.Pixels(v)
  def Centimeters(v: Int) = Length.Centimeters(v)
  def Percent(v: Int) = Length.Percent(v)
  def Points(v: Int) = Length("%spt".format(v))
}
