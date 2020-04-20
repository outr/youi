package io.youi.path

case class QuadraticCurveTo(x1: Double, y1: Double, x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    context.quadraticCurveTo(x + (x1 * scaleX), y + (y1 * scaleY), x + (this.x * scaleX), y + (this.y * scaleY))
  }

  override def toString: String = s"QuadraticCurveTo(x1: $x1, y1: $y1, x: $x, y: $y)"
}