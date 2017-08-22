package io.youi.easing

case class BackInOut(overshoot: Double = 1.70158) extends Easing {
  override def calculate(progress: Double): Double = {
    val t = progress / 0.5
    val o = overshoot * 1.525
    if (t < 1.0) {
      0.5 * (t * t * ((o + 1.0) * t - o))
    } else {
      val t2 = t - 2.0
      0.5 * (t2 * t2 * ((o + 1.0) * t2 + o) + 2.0)
    }
  }
}
