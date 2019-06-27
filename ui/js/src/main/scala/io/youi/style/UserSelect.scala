package io.youi.style

import io.youi.theme.Stringify

sealed abstract class UserSelect(val value: String)

object UserSelect extends Stringify[UserSelect] {
  case object None extends UserSelect("none")
  case object Auto extends UserSelect("auto")
  case object Text extends UserSelect("text")
  case object Contain extends UserSelect("contain")
  case object All extends UserSelect("all")
  case object Inherit extends UserSelect("inherit")
  case object Initial extends UserSelect("initial")
  case object Unset extends UserSelect("unset")

  private val list: List[UserSelect] = List(None, Auto, Text, Contain, All, Inherit, Initial, Unset)
  private val map: Map[String, UserSelect] = list.map(us => us.value -> us).toMap

  override def fromString(value: String): Option[UserSelect] = map.get(value)

  override def toString(value: UserSelect): Option[String] = Some(value.value)
}