package io.youi.font

import io.youi.drawable.{Context, TextDrawable}
import io.youi.paint.{Paint, Stroke}
import io.youi.spatial.BoundingBox

trait Text {
  def font: Font
  def text: String
  def size: Double
  def maxWidth: Double
  def kerning: Boolean
  def lines: Vector[Vector[CharacterPath]]

  lazy val boundingBox: BoundingBox = {
    val bb = BoundingBox.temp.zero()
    lines.foreach { line =>
      line.foreach { character =>
        bb.set(x2 = math.max(bb.x2, character.x + character.glyph.actualWidth(size)))
      }
    }
    bb.set(y1 = 0.0, y2 = lineHeight * lines.length)
    bb.immutable
  }

  def lineHeight: Double = font.lineHeight(size)

  def draw(context: Context, x: Double, y: Double, fill: Paint, stroke: Stroke): Unit

  def toDrawable(fill: Paint = Paint.none,
                 stroke: Stroke = Stroke.none): TextDrawable = new TextDrawable(this, fill, stroke)
}

object Text {
  case object empty extends Text {
    override def font: Font = Font.empty
    override def text: String = ""
    override def size: Double = 0.0
    override def maxWidth: Double = 0.0
    override def kerning: Boolean = false
    override def lines: Vector[Vector[CharacterPath]] = Vector.empty

    override def draw(context: Context, x: Double, y: Double, fill: Paint, stroke: Stroke): Unit = {}
  }
}