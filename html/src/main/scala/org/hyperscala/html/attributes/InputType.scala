package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class InputType(val value: String) extends EnumEntryAttributeValue

object InputType extends Enumerated[InputType] with EnumEntryPersistence[InputType] {
  case object Radio extends InputType("radio")
  case object Month extends InputType("month")
  case object Number extends InputType("number")
  case object URL extends InputType("url")
  case object Hidden extends InputType("hidden")
  case object Submit extends InputType("submit")
  case object Email extends InputType("email")
  case object Password extends InputType("password")
  case object DateTime extends InputType("datetime")
  case object File extends InputType("file")
  case object Button extends InputType("button")
  case object CheckBox extends InputType("checkbox")
  case object Week extends InputType("week")
  case object Text extends InputType("text")
  case object Tel extends InputType("tel")
  case object Date extends InputType("date")
  case object DateTimeLocal extends InputType("datetime-local")
  case object Search extends InputType("search")
  case object Image extends InputType("image")
  case object Reset extends InputType("reset")
  case object Color extends InputType("color")
  case object Time extends InputType("time")
  case object Range extends InputType("range")

  val values = findValues.toVector
}