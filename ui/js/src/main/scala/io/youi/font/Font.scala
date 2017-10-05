package io.youi.font

import io.youi.{Context, Drawable}
import io.youi.net.URL
import io.youi.path.Path
import io.youi.spatial.BoundingBox

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Font {
  def lineHeight(size: Double): Double
  def apply(text: String,
            size: Double,
            maxWidth: Double = Double.MaxValue,
            kerning: Boolean = true): Text
}

object Font {
  object empty extends Font {
    override def lineHeight(size: Double): Double = 0.0
    override def apply(text: String,
                       size: Double,
                       maxWidth: Double = Double.MaxValue,
                       kerning: Boolean = true): Text = Text.empty
  }
}

case class Text(font: Font,
                text: String,
                size: Double,
                maxWidth: Double,
                kerning: Boolean,
                lines: Vector[Vector[CharacterPath]]) extends Drawable {
  lazy val boundingBox: BoundingBox = {
    val bb = BoundingBox.temp.zero()
    lines.foreach { line =>
      line.foreach { character =>
        bb.set(x2 = math.max(bb.x2, character.x + character.glyph.width(size)))
      }
    }
    bb.set(y1 = 0.0, y2 = lineHeight * lines.length)
    bb.immutable
  }
  def lineHeight: Double = font.lineHeight(size)

  override def draw(context: Context): Unit = lines.foreach { line =>
    line.foreach(_.draw(context))
  }
}

object Text {
  lazy val empty: Text = Text(
    font = Font.empty,
    text = "",
    size = 0.0,
    maxWidth = 0.0,
    kerning = false,
    lines = Vector.empty
  )
}

case class CharacterPath(char: Char, size: Double, index: Int, x: Double, y: Double, glyph: Glyph) extends Drawable {
  override def draw(context: Context): Unit = glyph.draw(context, x, y, size)
}

trait Glyph {
  def width(size: Double): Double
  def draw(context: Context, x: Double, y: Double, size: Double): Unit
}

class OpenTypeFont(otf: opentype.Font) extends Font {
  private var glyphs = Map.empty[Char, OpenTypeGlyph]

  override def lineHeight(size: Double): Double = otf.ascender - otf.descender

  override def apply(text: String,
                     size: Double,
                     maxWidth: Double = Double.MaxValue,
                     kerning: Boolean = true): Text = {
    var offsetX = 0.0
    var offsetY = 0.0
    val lineHeight = this.lineHeight(size)
    var previous: Option[OpenTypeGlyph] = None
    val line = ListBuffer.empty[CharacterPath]
    val lines = ListBuffer.empty[Vector[CharacterPath]]
    val unitsPerEm = otf.unitsPerEm

    def processCharacters(chars: List[Char], index: Int = 0): Unit = if (chars.nonEmpty) {
      val char = chars.head
      if (char == '\n') {
        offsetX = 0.0
        offsetY += lineHeight
        previous = None
      } else {
        val glyph = glyphs.get(char) match {
          case Some(g) => g
          case None => {
            val g = OpenTypeGlyph(otf.charToGlyph(char.toString), unitsPerEm)
            glyphs += char -> g
            g
          }
        }
        var kernOffset = if (kerning && previous.nonEmpty) {
          otf.getKerningValue(previous.get.otg, glyph.otg)
        } else {
          0.0
        }
        previous = Some(glyph)
        if (offsetX + kernOffset + glyph.width(size) > maxWidth && line.nonEmpty) {
          offsetX = 0.0
          offsetY += lineHeight
          previous = None
          kernOffset = 0.0
          lines += line.toVector
          line.clear()
        }
        line += CharacterPath(char, size, index, offsetX, offsetY, glyph)
        offsetX += glyph.width(size)
      }
      processCharacters(chars.tail, index + 1)
    }

    processCharacters(text.toList)
    if (line.nonEmpty) {
      lines += line.toVector
    }
    Text(this, text, size, maxWidth, kerning, lines.toVector)
  }
}

case class OpenTypeGlyph(otg: opentype.Glyph, unitsPerEm: Double) extends Glyph {
//  lazy val path: Path = Path(otg.path.toPathData())
  override def width(size: Double): Double = otg.advanceWidth * (1.0 / unitsPerEm * size)

  override def draw(context: Context, x: Double, y: Double, size: Double): Unit = {
    context.save()
    context.scale(context.ratio, context.ratio)
    otg.draw(context.ctx, x, y, size)
    context.restore()
  }
}

object OpenTypeFont {
  private var pathMap = Map.empty[String, OpenTypeFont]

  def fromPath(path: String): Future[OpenTypeFont] = {
    pathMap.get(path) match {
      case Some(font) => Future.successful(font)
      case None => {
        opentype.OpenType.load(path).map { otf =>
          val font = new OpenTypeFont(otf)
          pathMap += path -> font
          font
        }
      }
    }
  }

  def fromURL(url: URL): Future[OpenTypeFont] = fromPath(url.toString)
}