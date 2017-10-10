package io.youi.font

sealed trait WrapMode

object WrapMode {
  case object None extends WrapMode
  case object Clip extends WrapMode
  case object Ellipsis extends WrapMode
  case object Hyphenate extends WrapMode
  case object Word extends WrapMode
}