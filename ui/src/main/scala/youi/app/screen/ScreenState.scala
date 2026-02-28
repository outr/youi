package youi.app.screen

sealed abstract class ScreenState(val index: Int, val working: Boolean) extends Ordered[ScreenState] {
  override def compare(that: ScreenState): Int = this.index.compare(that.index)

  def loaded: Boolean = this >= ScreenState.Loaded && this < ScreenState.Disposing
  def active: Boolean = this == ScreenState.Activated
}

object ScreenState {
  case object New extends ScreenState(0, false)
  case object Initializing extends ScreenState(1, true)
  case object Initialized extends ScreenState(2, false)
  case object Loading extends ScreenState(3, true)
  case object Loaded extends ScreenState(4, false)
  case object Activating extends ScreenState(5, true)
  case object Activated extends ScreenState(6, false)
  case object Deactivating extends ScreenState(7, true)
  case object Deactivated extends ScreenState(8, false)
  case object Disposing extends ScreenState(9, true)
  case object Disposed extends ScreenState(10, false)
}