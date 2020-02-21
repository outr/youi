package io.youi.ui.event

import io.youi.spatial.Point

case class PinchState(point1: Point, point2: Point, distance: Double)