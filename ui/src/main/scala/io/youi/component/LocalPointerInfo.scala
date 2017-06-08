package io.youi.component

import reactify.{Val, Var}

trait LocalPointerInfo {
  this: Component =>

  object pointer {
    val x: Val[Double] = Var(0.0)
    val y: Val[Double] = Var(0.0)
  }

  event.pointer.moveInside.attach { evt =>
    pointer.x.asInstanceOf[Var[Double]] := evt.x
    pointer.y.asInstanceOf[Var[Double]] := evt.y
  }
}
