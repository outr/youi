package org.hyperscala.tags.attributes

import org.powerscala.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
sealed case class InputType(value: String) extends EnumEntry[InputType] with AttributeValue

object InputType extends Enumerated[InputType] {
  val Button = InputType("button")
  val CheckBox = InputType("checkbox")
  val Color = InputType("color")
  val Date = InputType("date")
  val DateTime = InputType("datetime")
  val DateTimeLocal = InputType("datetime-local")
  val Email = InputType("email")
  val File = InputType("file")
  val Hidden = InputType("hidden")
  val Image = InputType("image")
  val Month = InputType("month")
  val Number = InputType("number")
  val Password = InputType("password")
  val Radio = InputType("radio")
  val Range = InputType("range")
  val Reset = InputType("reset")
  val Search = InputType("search")
  val Submit = InputType("submit")
  val Tel = InputType("tel")
  val Text = InputType("text")
  val Time = InputType("time")
  val Url = InputType("url")
  val Week = InputType("week")
}
