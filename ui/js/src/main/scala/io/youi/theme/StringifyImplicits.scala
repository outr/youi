package io.youi.theme

import io.youi.component.bootstrap.{ButtonSize, ButtonType}
import io.youi.{Color, Cursor}
import io.youi.paint.Paint
import io.youi.style._

trait StringifyImplicits {
  implicit def stringifyVisibility: Stringify[Visibility] = Visibility
  implicit def stringifyDisplay: Stringify[Display] = Display
  implicit def stringifyInputType: Stringify[InputType] = InputType
  implicit def stringifyHTMLBorder: Stringify[HTMLBorder] = HTMLBorder
  implicit def stringifyOverflow: Stringify[Overflow] = Overflow
  implicit def stringifyUserSelect: Stringify[UserSelect] = UserSelect
  implicit def stringifyPointerEvents: Stringify[PointerEvents] = PointerEvents
  implicit def stringifyButtonType: Stringify[ButtonType] = ButtonType
  implicit def stringifyButtonSize: Stringify[ButtonSize] = ButtonSize
  implicit def stringifyWhiteSpace: Stringify[WhiteSpace] = WhiteSpace
  implicit def stringifyPosition: Stringify[Position] = Position
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
  def pixels: Stringify[Double] = Stringify[Double](d => Some(s"${d}px")) { s => try {
    Some(s.replaceAllLiterally("px", "").toDouble)
  } catch {
    case _: Throwable => None
  }}
}

object StringifyImplicits extends StringifyImplicits