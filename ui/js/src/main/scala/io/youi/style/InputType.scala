package io.youi.style

sealed abstract class InputType(val value: String)

object InputType extends Stringify[InputType] {
  case object Button extends InputType("button")
  case object Checkbox extends InputType("checkbox")
  case object Color extends InputType("color")
  case object Date extends InputType("date")
  case object DateTimeLocal extends InputType("datetime-local")
  case object Email extends InputType("email")
  case object File extends InputType("file")
  case object Hidden extends InputType("hidden")
  case object Image extends InputType("image")
  case object Month extends InputType("month")
  case object Number extends InputType("number")
  case object Password extends InputType("password")
  case object Radio extends InputType("radio")
  case object Range extends InputType("range")
  case object Reset extends InputType("reset")
  case object Search extends InputType("search")
  case object Submit extends InputType("submit")
  case object Telephone extends InputType("tel")
  case object Text extends InputType("text")
  case object Time extends InputType("time")
  case object URL extends InputType("url")
  case object Week extends InputType("week")

  lazy val map: Map[String, InputType] = List(
    Button, Checkbox, Color, Date, DateTimeLocal, Email, File, Hidden, Image, Month, Number, Password, Radio, Range,
    Reset, Search, Submit, Telephone, Text, Time, URL, Week
  ).map(it => it.value -> it).toMap

  override def fromString(value: String): Option[InputType] = map.get(value.toLowerCase)

  override def toString(value: InputType): Option[String] = Some(value.value)
}