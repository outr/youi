package io.youi.component.types

import io.youi.Stringify

sealed abstract class Clear(val value: String)

object Clear extends Stringify[Clear] {
  case object None extends Clear("none")
  case object Left extends Clear("left")
  case object Right extends Clear("right")
  case object Both extends Clear("both")

  lazy val map: Map[String, Clear] = List(None, Left, Right, Both).map(f => f.value -> f).toMap

  override def fromString(value: String): Option[Clear] = map.get(value.toLowerCase)

  override def toString(value: Clear): Option[String] = if (value == None) {
    Option.empty[String]
  } else {
    Some(value.value)
  }
}