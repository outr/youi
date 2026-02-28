package youi.easing

object Linear extends Easing {
  override def calculate(progress: Double): Double = progress
}
