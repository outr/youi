package io.youi.component

import reactify.Var

trait LocalMouseInfo {
  this: Component =>

  protected val mouseX = Var(0.0)
  protected val mouseY = Var(0.0)

  event.mouse.moveInside.attach { evt =>
    mouseX := evt.x
    mouseY := evt.y
  }
}
