package youi.component.types

import youi.Stringify

sealed abstract class WhiteSpace(val value: String)

object WhiteSpace extends Stringify[WhiteSpace] {
  case object Normal extends WhiteSpace("normal")
  case object NoWrap extends WhiteSpace("nowrap")
  case object Pre extends WhiteSpace("pre")
  case object PreWrap extends WhiteSpace("pre-wrap")
  case object PreLine extends WhiteSpace("pre-line")

  lazy val map: Map[String, WhiteSpace] = List(Normal, NoWrap, Pre, PreWrap, PreLine).map(ws => ws.value -> ws).toMap

  override def fromString(value: String): Option[WhiteSpace] = map.get(value.toLowerCase)

  override def toString(value: WhiteSpace): Option[String] = Some(value.value)
}