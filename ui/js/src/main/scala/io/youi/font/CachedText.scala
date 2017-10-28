package io.youi.font
import io.youi.drawable.Context
import io.youi.paint.{Paint, Stroke}
import io.youi.ui
import io.youi.util.CanvasPool

case class CachedText(font: CachedFont,
                      text: String,
                      size: Double,
                      maxWidth: Double,
                      kerning: Boolean,
                      lines: Vector[Vector[CharacterPath]]) extends Text {
  override def draw(context: Context, x: Double, y: Double, fill: Paint, stroke: Stroke): Unit = {
    CanvasPool.withCanvas(boundingBox.width, boundingBox.height, ui.ratio) { canvas =>
      val ctx = new Context(canvas, ui.ratio)

      // Draw all the characters to our temporary canvas
      lines.foreach { line =>
        line.foreach { path =>
          // TODO: font.cache(size)(path.glyph)
        }
      }

      // Fill and stroke
      // TODO: set composite
      // TODO: fill the entire canvas

      // Draw the canvas to the context now
      context.save()
      val scale = CanvasPool.scale(ui.ratio)
      context.scale(scale, scale)
      context.drawCanvas(canvas)(x, y)
      context.restore()
    }
  }
}