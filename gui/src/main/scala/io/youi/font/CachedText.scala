package io.youi.font

import io.youi.util.CanvasPool
import io.youi.drawable.{Composite, Context}
import io.youi.paint.{Paint, Stroke}
import io.youi._

case class CachedText(font: CachedFont,
                      text: String,
                      size: Double,
                      maxWidth: Double,
                      kerning: Boolean,
                      lines: Vector[Vector[CharacterPath]]) extends Text {
  override def draw(context: Context, x: Double, y: Double, fill: Paint, stroke: Stroke): Unit = {
    CanvasPool.withCanvas(boundingBox.width, boundingBox.height, ratio) { canvas =>
      val ctx = new Context(canvas, ratio)
      ctx.reset()
      ctx.save()
      val scale = CanvasPool.scale(ratio)
      ctx.scale(scale, scale)

      // Draw all the characters to our temporary canvas
      lines.foreach { line =>
        line.foreach { path =>
          val cached = font(size)(path.glyph)
          cached.draw(ctx, path.x * ratio, path.y * ratio)
        }
      }

      // Fill and stroke
      ctx.composite(Composite.SourceIn)
      ctx.rect(0.0, 0.0, canvas.width, canvas.height)
      ctx.fill(fill, apply = true)
      ctx.restore()

      // Draw the canvas to the context now
      context.save()
      context.scale(scale, scale)
      context.drawCanvas(canvas)(x, y)
      context.restore()
    }
  }
}