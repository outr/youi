package org.hyperscala

import css.attributes._
import html.tag.Text
import math._

import language.implicitConversions
import argonaut._
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

  implicit class EnhancedJsonObject(json: JsonObject) {
    private def conv[T](key: String, f: Json => Option[T]) = json(key).map(j => f(j)).flatten
    def stringOption(key: String) = conv(key, j => j.string)
    def booleanOption(key: String) = conv(key, j => j.bool)
    def intOption(key: String) = longOption(key).map(l => l.toInt)
    def longOption(key: String) = doubleOption(key).map(d => math.round(d))
    def doubleOption(key: String) = conv(key, j => j.number.map(n => n.toDouble))
    def stringMapOption(key: String) = conv(key, j => j.obj).map(obj => obj.toMap.map {
      case (k, v) => k -> v.stringOrEmpty
    })

    def listOption(key: String) = conv(key, j => j.array).map(array => array.toList)
    def listConvertedOption[T](key: String, converter: Json => T) = listOption(key).map(list => list.map(j => converter(j)))
    def stringsOption(key: String) = listConvertedOption[String](key, (json: Json) => json.stringOrEmpty)
    def doublesOption(key: String) = listConvertedOption[Double](key, (json: Json) => json.numberOrZero.toDouble)

    def string(key: String, default: => String = "") = stringOption(key).getOrElse(default)
    def int(key: String, default: => Int = 0) = intOption(key).getOrElse(default)
    def long(key: String, default: => Long = 0) = longOption(key).getOrElse(default)
    def double(key: String, default: => Double = 0.0) = doubleOption(key).getOrElse(default)
    def boolean(key: String, default: => Boolean = false) = booleanOption(key).getOrElse(default)
    def strings(key: String, default: => List[String] = Nil) = stringsOption(key).getOrElse(default)
    def stringMap(key: String, default: => Map[String, String] = Map.empty) = stringMapOption(key).getOrElse(default)
    def list(key: String, default: => List[Json] = Nil) = listOption(key).getOrElse(default)

    def toJson = Json.jObject(json)

    def as[T](implicit codec: CodecJson[T]) = toJson.as[T].getOr(throw new NullPointerException(s"Unable to parse: $json to case class!"))
    def as[T](key: String)(implicit codec: CodecJson[T]) = json(key).map(j => j.as[T].getOr(throw new NullPointerException(s"Unable to parse: $json[$key] to case class!"))).get
//    def as[T](implicit codec: CodecJson[T]) = codec.decodeJson(Json.jObject(json)).getOr(throw new NullPointerException(s"Unable to parse: $json to case class!"))
//    def as[T](key: String)(implicit codec: CodecJson[T]) = codec.decodeJson(json.apply(key).get).getOr(throw new NullPointerException(s"Unable to parse: $json[$key] to case class!"))
  }
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