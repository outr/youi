package youi.component.types

import youi.Stringify

sealed abstract class FontWeight(val value: String)

object FontWeight extends Stringify[FontWeight] {
  case object Normal extends FontWeight("normal")
  case object Bold extends FontWeight("bold")
  case object Bolder extends FontWeight("bolder")
  case object Lighter extends FontWeight("lighter")
  case object W100 extends FontWeight("100")
  case object W200 extends FontWeight("200")
  case object W300 extends FontWeight("300")
  case object W400 extends FontWeight("400")
  case object W500 extends FontWeight("500")
  case object W600 extends FontWeight("600")
  case object W700 extends FontWeight("700")
  case object W800 extends FontWeight("800")
  case object W900 extends FontWeight("900")
  case object Inherit extends FontWeight("inherit")
  case object Initial extends FontWeight("initial")
  case object Unset extends FontWeight("unset")

  lazy val map: Map[String, FontWeight] = List(
    Normal, Bold, Bolder, Lighter,
    W100, W200, W300, W400, W500, W600, W700, W800, W900,
    Inherit, Initial, Unset
  ).map(fw => fw.value -> fw).toMap

  override def fromString(value: String): Option[FontWeight] = map.get(value.toLowerCase)

  override def toString(value: FontWeight): Option[String] = if (value == Initial) {
    Option.empty[String]
  } else {
    Some(value.value)
  }
}
