package youi.drawable

import youi.font.Text
import youi.paint.{Paint, Stroke}

class TextDrawable(text: Text, fill: Paint, stroke: Stroke) extends Drawable {
  override def draw(context: Context, x: Double, y: Double): Unit = text.draw(context, x, y, fill, stroke)
}