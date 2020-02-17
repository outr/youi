package io.youi.gui.event

import io.youi.gui.Component
import reactify.Var
import reactify.reaction.{Reaction, ReactionStatus}

class Tap(component: Component) extends Reaction[Pointer] {
  val enabled: Var[Boolean] = Var(Tap.DefaultEnabled)
  val maxDistance: Var[Double] = Var(Tap.DefaultMaxDistance)
  val maxTimeout: Var[Long] = Var(Tap.DefaultMaxTimeout)

  override def apply(value: Pointer, previous: Option[Pointer]): ReactionStatus = {
    ReactionStatus.Continue
  }
}

object Tap {
  val DefaultEnabled: Boolean = false
  val DefaultMaxDistance: Double = 10.0
  val DefaultMaxTimeout: Long = 200L
}