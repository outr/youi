package io.youi.component.bootstrap

import io.youi.theme.Stringify

sealed abstract class ButtonSize(key: String) {
  private val className: String = s"btn-$key"
}

object ButtonSize extends Stringify[ButtonSize] {
  case object Large extends ButtonSize("lg")
  case object Normal extends ButtonSize("nm")
  case object Small extends ButtonSize("sm")

  private val map = List(Large, Normal, Small).map(bs => bs.className -> bs).toMap

  override def fromString(value: String): Option[ButtonSize] = map.get(value)

  override def toString(value: ButtonSize): Option[String] = Option(value.className)
}