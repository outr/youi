package io.youi.drawable

trait Clipped extends Drawable {
  def x1: Double
  def y1: Double
  def x2: Double
  def y2: Double
  def drawable: Drawable

  override def draw(context: Context, x: Double, y: Double): Unit = {
    Clipped.draw(context, x, y, x1, y1, x2, y2, drawable)
  }
}

case class ClippedInstance(x1: Double, y1: Double, x2: Double, y2: Double, drawable: Drawable) extends Clipped

object Clipped {
  def draw(context: Context,
            x: Double,
            y: Double,
            x1: Double,
            y1: Double,
            x2: Double,
            y2: Double,
            drawable: Drawable): Unit = {
    context.save()
    context.clipRect(x1 + x, y1 + y, x2 + x, y2 + y)
    drawable.draw(context, x, y)
    context.restore()
  }

  def apply(x1: Double, y1: Double, x2: Double, y2: Double)(drawable: Drawable): Clipped = {
    ClippedInstance(x1, y1, x2, y2, drawable)
  }
}