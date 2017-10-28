package io.youi.font

import io.youi.drawable.Context
import io.youi.path.Path

trait Glyph {
  def font: Font
  def char: Char
  def path: Path
  def sizedPath(size: Double): Path
  def width(size: Double): Double
  def draw(context: Context, x: Double, y: Double, size: Double): Unit
}