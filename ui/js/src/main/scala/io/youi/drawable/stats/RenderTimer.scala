package io.youi.drawable.stats

class RenderTimer(drawable: Drawable) extends RenderStats with Drawable {
  modified := drawable.modified

  override def draw(context: Context, x: Double, y: Double): Unit = {
    draw(drawable, context, x, y)
    scribe.info(s"Drawn in $current seconds.")
  }
}

object RenderTimer {
  def apply(drawable: Drawable): RenderTimer = new RenderTimer(drawable)
}