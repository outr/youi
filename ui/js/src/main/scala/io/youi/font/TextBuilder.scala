package io.youi.font

import scala.annotation.tailrec

class TextBuilder(val font: Font, val text: String, val size: Double, val maxWidth: Double, val kerning: Boolean) {
  private var index = 0
  private var lines = List.empty[List[CharacterPath]]

  val lineHeight: Double = font.lineHeight(size)

  def measureWidth(line: String): Double = {
    var width = 0.0
    processLine(line, new CharacterProcessor {
      override def process(glyph: Glyph, offset: Double): Boolean = {
        width = offset + glyph.width(size)
        true
      }
    })
    width
  }

  def maximum(line: String): String = {
    val b = new StringBuilder
    processLine(line, new CharacterProcessor {
      override def process(glyph: Glyph, offset: Double): Boolean = {
        if (offset + glyph.width(size) < maxWidth) {
          b.append(glyph.char)
          true
        } else {
          false
        }
      }
    })
    b.toString()
  }

  def addLine(line: String): Unit = {
    val y = lines.length * lineHeight
    var characters = List.empty[CharacterPath]
    processLine(line, new CharacterProcessor {
      override def process(glyph: Glyph, offset: Double): Boolean = {
        val c = CharacterPath(glyph.char, size, index, offset, y, glyph)
        characters = c :: characters
        index += 1
        true
      }
    })
    lines = characters :: lines
  }

  def processLine(line: String, processor: CharacterProcessor): Unit = {
   processCharacters(line.toList, processor, None, 0.0)
  }

  @tailrec
  private def processCharacters(characters: List[Char],
                                processor: CharacterProcessor,
                                previous: Option[Glyph],
                                offset: Double): Unit = if (characters.nonEmpty) {
    val glyph = font.glyph(characters.head)
    val kerning = if (this.kerning) {
      previous.map(font.kerning(_, glyph, size)).getOrElse(0.0)
    } else {
      0.0
    }
    if (!processor.process(glyph, offset + kerning)) {
      // Premature termination from processor
    } else {
      processCharacters(characters.tail, processor, Some(glyph), offset + kerning + glyph.width(size))
    }
  }

  def toText: Text = Text(font, text, size, maxWidth, kerning, lines.reverse.map(_.reverse.toVector).toVector)
}

trait CharacterProcessor {
  def process(glyph: Glyph, offset: Double): Boolean
}