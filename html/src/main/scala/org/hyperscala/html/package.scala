package org.hyperscala

import css.attributes._
import math._

package object html {
  /**
   * Support conversion from String to Text instance
   */
  implicit def s2Text(s: String) = new Text(s)

  implicit def int2LengthInt(i: Int) = LengthInt(i)
  implicit def double2LengthInt(d: Double) = LengthInt(round(d).toInt)
  implicit def int2FontSizeInt(i: Int) = FontSizeInt(i)
  implicit def double2FontSizeInt(d: Double) = FontSizeInt(round(d).toInt)
  implicit def l2Fs(l: Length) = FontSize(l.value)
}

case class LengthInt(i: Int) {
  def px = Length.Pixels(i)
  def % = Length.Percent(i)
  def pct = Length.Percent(i)
  def cm = Length.Centimeters(i)
}

case class FontSizeInt(i: Int) {
  def pt = FontSize.Points(i)
}