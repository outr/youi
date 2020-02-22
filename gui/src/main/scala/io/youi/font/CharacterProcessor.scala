package io.youi.font

trait CharacterProcessor {
  def process(glyph: Glyph, offset: Double): Boolean
}
