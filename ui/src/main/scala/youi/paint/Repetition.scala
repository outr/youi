package youi.paint

sealed abstract class Repetition(val value: String)

object Repetition {
  case object Repeat extends Repetition("repeat")
  case object RepeatX extends Repetition("repeat-x")
  case object RepeatY extends Repetition("repeat-y")
  case object NoRepeat extends Repetition("no-repeat")
}