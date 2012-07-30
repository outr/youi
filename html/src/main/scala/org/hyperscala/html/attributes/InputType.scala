package org.hyperscala.html.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class InputType(val value: String) extends EnumEntry[InputType] with AttributeValue

object InputType extends Enumerated[InputType] with EnumEntryPersistence[InputType] {
  val Radio = new InputType("radio")
  val Month = new InputType("month")
  val Number = new InputType("number")
  val URL = new InputType("url")
  val Hidden = new InputType("hidden")
  val Submit = new InputType("submit")
  val Email = new InputType("email")
  val Password = new InputType("password")
  val DateTime = new InputType("datetime")
  val File = new InputType("file")
  val Button = new InputType("button")
  val CheckBox = new InputType("checkbox")
  val Week = new InputType("week")
  val Text = new InputType("text")
  val Tel = new InputType("tel")
  val Date = new InputType("date")
  val DateTimeLocal = new InputType("datetime-local")
  val Search = new InputType("search")
  val Image = new InputType("image")
  val Reset = new InputType("reset")
  val Color = new InputType("color")
  val Time = new InputType("time")
  val Range = new InputType("range")
}