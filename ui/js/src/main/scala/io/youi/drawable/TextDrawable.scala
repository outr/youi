package io.youi.drawable

class TextDrawable(text: Text, fill: Paint, stroke: Stroke) extends Drawable {
  override def draw(context: Context, x: Double, y: Double): Unit = text.draw(context, x, y, fill, stroke)
}