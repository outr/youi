package io.youi.drawable

class RenderTimer(drawable: Drawable) extends Drawable {
  modified := drawable.modified

  override def draw(context: Context, x: Double, y: Double): Unit = {
    val start = System.currentTimeMillis()
    drawable.draw(context, x, y)
    val elapsed = (System.currentTimeMillis() - start) / 1000.0
    scribe.info(s"Drawn in $elapsed seconds.")
  }
}

object RenderTimer {
  def apply(drawable: Drawable): RenderTimer = new RenderTimer(drawable)
}