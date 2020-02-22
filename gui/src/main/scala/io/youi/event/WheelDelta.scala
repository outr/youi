package io.youi.event

case class WheelDelta(x: Double, y: Double, z: Double, mode: DeltaMode) {
  override def toString: String = s"WheelData(x: $x, y: $y, z: $z, mode: $mode)"
}
