package youi.easing

case class BackIn(overshoot: Double) extends Easing {
  override def calculate(progress: Double): Double = {
    val t = progress
    t * t * ((overshoot + 1.0) * t - overshoot)
  }
}
