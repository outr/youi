package youi.font

import youi.dom
import youi.drawable.Context
import youi.spatial.Size
import org.scalajs.dom._

case class CanvasFont(family: String, style: String, variant: String, weight: String) extends Font {
  override def lineHeight(size: Double): Double = CanvasFont.measure("H", this, size).height

  override def ascender(size: Double): Double = 0.0

  override def descender(size: Double): Double = 0.0

  override def glyph(char: Char): Glyph = ???

  override def kerning(first: Glyph, second: Glyph, size: Double): Double = ???

  override protected def createBuilder(text: String, size: Double, maxWidth: Double, kerning: Boolean): TextBuilder = ???

  def apply(context: Context, size: Double): Unit = {
    val value = s"${size}px $family"
    scribe.info(s"Setting: $value")
    context.ctx.font = value
  }
}

object CanvasFont {
  private lazy val canvas = dom.create[html.Canvas]("canvas")
  private lazy val context = new Context(canvas, 1.0)

  def measure(text: String, font: CanvasFont, size: Double): Size = {
    font(context, size)
    context.measureText(text)
  }
}