package io.youi.event

case class SwipeEvent(direction: Swipe.Direction,
                      pointer: Pointer,
                      distance: Double,
                      acceleration: Double)
