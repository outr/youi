package youi.component.types

import youi.Stringify

sealed abstract class AlignItems(val value: String)

object AlignItems extends Stringify[AlignItems] {
  case object Normal extends AlignItems("normal")
  case object Stretch extends AlignItems("stretch")
  case object Center extends AlignItems("center")
  case object Start extends AlignItems("start")
  case object End extends AlignItems("end")
  case object FlexStart extends AlignItems("flex-start")
  case object FlexEnd extends AlignItems("flex-end")
  case object Baseline extends AlignItems("baseline")
  case object Inherit extends AlignItems("inherit")
  case object Initial extends AlignItems("initial")
  case object Unset extends AlignItems("unset")

  lazy val map: Map[String, AlignItems] = List(
    Normal, Stretch, Center, Start, End, FlexStart, FlexEnd, Baseline, Inherit, Initial, Unset
  ).map(a => a.value -> a).toMap

  override def fromString(value: String): Option[AlignItems] = map.get(value.toLowerCase)

  override def toString(value: AlignItems): Option[String] = if (value == Initial) {
    Option.empty[String]
  } else {
    Some(value.value)
  }
}
