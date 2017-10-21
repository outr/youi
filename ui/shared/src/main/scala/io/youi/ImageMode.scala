package io.youi

sealed trait ImageMode

object ImageMode {
  case object Quality extends ImageMode
  case object Speed extends ImageMode
}