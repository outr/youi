package io.youi.component.event

import reactify.{Val, Var}

object Mouse {
  val x: Val[Double] = Var(0.0)
  val y: Val[Double] = Var(0.0)
}
