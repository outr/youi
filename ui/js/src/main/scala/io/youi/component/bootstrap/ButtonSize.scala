package io.youi.component.bootstrap

import io.youi.component.extras.Classifiable

sealed abstract class ButtonSize(key: String) {
  private val className: String = s"btn-$key"
}

object ButtonSize extends Classifiable[ButtonSize] {
  case object Large extends ButtonSize("lg")
  case object Normal extends ButtonSize("nm")
  case object Small extends ButtonSize("sm")

  private val map = List(Large, Normal, Small).map(bs => bs.className -> bs).toMap

  override def fromString(value: String): Option[ButtonSize] = map.get(value)

  override def toString(value: ButtonSize): String = value.className
}