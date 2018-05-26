package io.youi.theme

import io.youi.{Color, Cursor, Length}
import io.youi.paint.Paint
import io.youi.style.{FontFamily, FontWeight, Position}
import reactify.DepConnector

trait StringifyImplicits {
  implicit def stringifyPosition: Stringify[Position] = Position
  implicit def stringifyLength: Stringify[Length] = Length
  implicit def stringifyPaint: Stringify[Paint] = Paint
  implicit def stringifyCursor: Stringify[Cursor] = Cursor
  implicit def stringifyFontFamily: Stringify[FontFamily] = FontFamily
  implicit def stringifyFontWeight: Stringify[FontWeight] = FontWeight
  implicit val stringifyColor: Stringify[Color] = Stringify[Color](c => Some(c.asCSS()))(Color.unapply)
  implicit def stringifyBoolean: Stringify[Boolean] = Stringify[Boolean](b => Some(b.toString)) {
    case "true" => Some(true)
    case "false" => Some(false)
    case _ => None
  }
  implicit def stringifyString: Stringify[String] = Stringify[String](Some(_))(Some(_))
  implicit def stringifyInt: Stringify[Int] = Stringify[Int](i => Some(i.toString)) { s => try {
    Some(s.toInt)
  } catch {
    case _: Throwable => None
  }}
  implicit def stringifyDouble: Stringify[Double] = Stringify[Double](d => Some(d.toString)) { s => try {
    Some(s.toDouble)
  } catch {
    case _: Throwable => None
  }}

  implicit val lengthConnector: DepConnector[Length, Length] = new DepConnector[Length, Length] {
    override def combine(variable: => Length, adjustment: => Length): Length = variable + adjustment

    override def extract(value: => Length, adjustment: => Length): Length = value - adjustment
  }
}

object StringifyImplicits extends StringifyImplicits