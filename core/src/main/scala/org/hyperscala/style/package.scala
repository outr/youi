package org.hyperscala

import style.{FontSize, Length}

import scala.math._

package object style {
  implicit def s2Resource(s: String): Resource = Resource(s)
  implicit def int2Convertable(i: Int) = ConvertableInt(i)
  implicit def double2Convertable(d: Double) = ConvertableInt(round(d).toInt)

  def xxSmall = FontSize.XXSmall
  def xSmall = FontSize.XSmall
  def small = FontSize.Small
  def medium = FontSize.Medium
  def large = FontSize.Large
  def xLarge = FontSize.XLarge
  def xxLarge = FontSize.XXLarge
  def smaller = FontSize.Smaller
  def larger = FontSize.Larger
}

case class ConvertableInt(i: Int) {
  def px = Length.Pixels(i)
  def % = Length.Percent(i)
  def pct = Length.Percent(i)
  def cm = Length.Centimeters(i)
  def pt = FontSize.Points(i)
}