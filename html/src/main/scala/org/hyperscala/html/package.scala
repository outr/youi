package org.hyperscala

import css.attributes._
import html.tag.Text
import math._

import language.implicitConversions
import org.powerscala.Unique

package object html {
  implicit def s2Text(s: String) = new Text(s)      // Support conversion from String to Text instance

  implicit def int2LengthInt(i: Int): LengthInt = LengthInt(i)
  implicit def double2LengthInt(d: Double): LengthInt = LengthInt(round(d).toInt)
  implicit def int2FontSizeInt(i: Int): FontSizeInt = FontSizeInt(i)
  implicit def double2FontSizeInt(d: Double): FontSizeInt = FontSizeInt(round(d).toInt)
  implicit def l2Fs(l: Length): FontSize = FontSize(l.value)
  implicit def int2ZIndex(i: Int): ZIndex = ZIndex.Numeric(i)

  implicit def it2Rit[T <: IdentifiableTag](t: T): ReIdentifiable[T] = ReIdentifiable[T](t)

  implicit def tag2CopyableTag[T <: HTMLTag](t: T): CopyableHTMLTag[T] = new CopyableHTMLTag[T](t)
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

case class ReIdentifiable[T <: IdentifiableTag](t: T) {
  def reId = {
    t.id := Unique()
    t
  }
}