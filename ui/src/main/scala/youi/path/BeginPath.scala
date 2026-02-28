package youi.path

import youi.drawable.Context

object BeginPath extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = context.begin()

  override def toString: String = "BeginPath"
}
