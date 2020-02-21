package io.youi.ui.event

sealed trait DeltaMode

object DeltaMode {
  case object Pixel extends DeltaMode
  case object Line extends DeltaMode
  case object Page extends DeltaMode
}