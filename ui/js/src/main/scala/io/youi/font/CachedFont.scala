package io.youi.font

import io.youi.drawable.{Context, Drawable}
import io.youi.util.CanvasPool
import io.youi.{Color, ui}
import org.scalajs.dom._
import reactify._

class CachedFont(font: Font) extends Font {
  private var map = Map.empty[Double, CachedFontSize]

  override def lineHeight(size: Double): Double = font.lineHeight(size)

  override def ascender(size: Double): Double = font.ascender(size)

  override def descender(size: Double): Double = font.descender(size)

  override def glyph(char: Char): Glyph = font.glyph(char)

  override def kerning(first: Glyph, second: Glyph, size: Double): Double = font.kerning(first, second, size)

  override protected def createBuilder(text: String, size: Double, maxWidth: Double, kerning: Boolean): TextBuilder = {
    new CachedTextBuilder(this, text, size, maxWidth, kerning)
  }

  def apply(size: Double): CachedFontSize = map.get(size) match {
    case Some(cfs) => cfs
    case None => {
      val cfs = new CachedFontSize(this, size)
      map += size -> cfs
      cfs
    }
  }
}

class CachedFontSize(font: CachedFont, val size: Double) {
  private var map = Map.empty[Glyph, CachedGlyph]

  def apply(glyph: Glyph): CachedGlyph = map.get(glyph) match {
    case Some(c) => c
    case None => {
      val c = new CachedGlyph(this, glyph)
      map += glyph -> c
      c
    }
  }
}

class CachedGlyph(size: CachedFontSize, glyph: Glyph) extends Drawable {
  private val canvas = Val(CanvasPool(glyph.actualWidth(size.size), glyph.font.lineHeight(size.size), ui.ratio))
  canvas.changes {
    case (oldValue, newValue) => {
      update()
      CanvasPool.restore(oldValue)
      document.body.removeChild(oldValue)
      document.body.appendChild(newValue)
    }
  }
  update()

  private def update(): Unit = {
    val context = new Context(canvas, ui.ratio)
    context.clear()
    glyph.draw(context, 0.0, 0.0, size.size)
    context.fill(Color.White, apply = true)
    modified := System.currentTimeMillis()
  }

  override def draw(context: Context, x: Double, y: Double): Unit = context.drawCanvas(canvas)(x, y)
}