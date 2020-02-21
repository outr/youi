package io.youi.ui.event

case class SwipeEvent(direction: Swipe.Direction,
                      pointer: Pointer,
                      distance: Double,
                      acceleration: Double)
