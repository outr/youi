package io.youi.path

object ClosePath extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = context.close()

  override def toString: String = "ClosePath"
}
