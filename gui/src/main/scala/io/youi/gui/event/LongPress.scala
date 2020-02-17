package io.youi.gui.event

import io.youi.gui.Component
import reactify.Var
import reactify.reaction.{Reaction, ReactionStatus}

class LongPress(component: Component) extends Reaction[Pointer] {
  val enabled: Var[Boolean] = Var(LongPress.DefaultEnabled)
  val minTimeout: Var[Long] = Var(LongPress.DefaultMinTimeout)

  override def apply(value: Pointer, previous: Option[Pointer]): ReactionStatus = ReactionStatus.Continue
}

object LongPress {
  val DefaultEnabled: Boolean = true
  val DefaultMinTimeout: Long = 1000L
}