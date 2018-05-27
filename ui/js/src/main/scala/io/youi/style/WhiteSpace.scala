package io.youi.style

import io.youi.theme.Stringify

sealed abstract class WhiteSpace(val value: String)

object WhiteSpace extends Stringify[WhiteSpace] {
  case object Normal extends WhiteSpace("normal")
  case object NoWrap extends WhiteSpace("nowrap")
  case object Pre extends WhiteSpace("pre")
  case object PreWrap extends WhiteSpace("pre-wrap")
  case object PreLine extends WhiteSpace("pre-line")

  lazy val map = List(Normal, NoWrap, Pre, PreWrap, PreLine).map(ws => ws.value -> ws).toMap
}