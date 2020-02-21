package io.youi.ui.event

case class PinchEvent(pointer: Pointer,
                      previous: PinchState,
                      current: PinchState,
                      deltaX: Double,
                      deltaY: Double,
                      deltaDistance: Double,
                      direction: PinchDirection)
