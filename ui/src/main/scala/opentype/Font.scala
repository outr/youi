package opentype

import org.scalajs.dom.CanvasRenderingContext2D

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("opentype.Font")
class Font extends js.Object {
  def glyphs: js.Array[Glyph] = js.native
  def unitsPerEm: Double = js.native
  def ascender: Double = js.native
  def descender: Double = js.native

  def getPath(text: String,
              x: Double = js.native,
              y: Double = js.native,
              fontSize: Double = js.native,
              options: PathOptions = js.native): Path = js.native
  def getPaths(text: String,
               x: Double = js.native,
               y: Double = js.native,
               fontSize: Double = js.native,
               options: PathOptions = js.native): js.Array[Path] = js.native
  def draw(ctx: CanvasRenderingContext2D,
           text: String,
           x: Double = js.native,
           y: Double = js.native,
           fontSize: Double = js.native,
           options: PathOptions = js.native): Path = js.native
  def drawPoints(ctx: CanvasRenderingContext2D,
                 text: String,
                 x: Double = js.native,
                 y: Double = js.native,
                 fontSize: Double = js.native,
                 options: PathOptions = js.native): Path = js.native
  def drawMetrics(ctx: CanvasRenderingContext2D,
                  text: String,
                  x: Double = js.native,
                  y: Double = js.native,
                  fontSize: Double = js.native,
                  options: PathOptions = js.native): Path = js.native
  def stringToGlyphs(text: String): js.Array[Glyph] = js.native
  def charToGlyph(char: String): Glyph = js.native
  def getKerningValue(leftGlyph: Glyph, rightGlyph: Glyph): Double = js.native
  def getAdvanceWidth(text: String, fontSize: Double = js.native, options: PathOptions = js.native): Double = js.native
  def download(): Unit = js.native
}

trait PathOptions extends js.Object {
  var kerning: js.UndefOr[Boolean] = js.undefined
  var features: js.UndefOr[OpenTypeFeatureTags] = js.undefined
  var hinting: js.UndefOr[Boolean] = js.undefined
}

trait OpenTypeFeatureTags extends js.Object {
  var liga: js.UndefOr[Boolean] = js.undefined
  var rlig: js.UndefOr[Boolean] = js.undefined
}