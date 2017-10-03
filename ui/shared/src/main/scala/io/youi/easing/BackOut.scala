package io.youi.easing

case class BackOut(overshoot: Double = 1.70158) extends Easing {
  override def calculate(progress: Double): Double = {
    val t = progress - 1.0
    t * t * ((overshoot + 1.0) * t + overshoot) + 1
  }
}
