package io.youi.path

case class MoveTo(x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    context.moveTo(x + (this.x * scaleX), y + (this.y * scaleY))
  }

  override def toString: String = s"MoveTo(x: $x, y: $y)"
}
