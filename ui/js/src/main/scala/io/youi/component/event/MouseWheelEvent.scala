package io.youi.component.event

import io.youi.Point
import io.youi.component.Component

class MouseWheelEvent(component: Component,
                      val x: Double,
                      val y: Double,
                      val globalX: Double,
                      val globalY: Double,
                      val delta: WheelDelta) {
  lazy val local: Point = Point(x, y)
  lazy val global: Point = Point(globalX, globalY)
}