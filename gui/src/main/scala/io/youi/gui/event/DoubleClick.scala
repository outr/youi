package io.youi.gui.event

import io.youi.gui.Component
import reactify.Var
import reactify.reaction.{Reaction, ReactionStatus}

class DoubleClick(component: Component) extends Reaction[Pointer] {
  val enabled: Var[Boolean] = Var(DoubleClick.DefaultEnabled)
  val maxDelay: Var[Long] = Var(DoubleClick.DefaultMaxDelay)

  override def apply(value: Pointer, previous: Option[Pointer]): ReactionStatus = ReactionStatus.Continue
}

object DoubleClick {
  val DefaultEnabled: Boolean = true
  val DefaultMaxDelay: Long = 400L
}