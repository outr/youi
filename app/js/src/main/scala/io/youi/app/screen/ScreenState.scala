package io.youi.app.screen

sealed abstract class ScreenState(val index: Int)

object ScreenState {
  case object New extends ScreenState(0)
  case object Initializing extends ScreenState(1)
  case object Initialized extends ScreenState(2)
  case object Loading extends ScreenState(3)
  case object Loaded extends ScreenState(4)
  case object Activating extends ScreenState(5)
  case object Activated extends ScreenState(6)
  case object Deactivating extends ScreenState(7)
  case object Deactivated extends ScreenState(8)
  case object Disposing extends ScreenState(9)
  case object Disposed extends ScreenState(10)
}