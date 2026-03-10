package youi.component.types

import youi.Stringify

sealed abstract class JustifyContent(val value: String)

object JustifyContent extends Stringify[JustifyContent] {
  case object Normal extends JustifyContent("normal")
  case object Center extends JustifyContent("center")
  case object Start extends JustifyContent("start")
  case object End extends JustifyContent("end")
  case object FlexStart extends JustifyContent("flex-start")
  case object FlexEnd extends JustifyContent("flex-end")
  case object Left extends JustifyContent("left")
  case object Right extends JustifyContent("right")
  case object SpaceBetween extends JustifyContent("space-between")
  case object SpaceAround extends JustifyContent("space-around")
  case object SpaceEvenly extends JustifyContent("space-evenly")
  case object Stretch extends JustifyContent("stretch")
  case object Inherit extends JustifyContent("inherit")
  case object Initial extends JustifyContent("initial")
  case object Unset extends JustifyContent("unset")

  lazy val map: Map[String, JustifyContent] = List(
    Normal, Center, Start, End, FlexStart, FlexEnd, Left, Right,
    SpaceBetween, SpaceAround, SpaceEvenly, Stretch, Inherit, Initial, Unset
  ).map(j => j.value -> j).toMap

  override def fromString(value: String): Option[JustifyContent] = map.get(value.toLowerCase)

  override def toString(value: JustifyContent): Option[String] = if (value == Initial) {
    Option.empty[String]
  } else {
    Some(value.value)
  }
}
