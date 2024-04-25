package opentype

import org.scalajs.dom.CanvasRenderingContext2D

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("opentype.Glyph")
class Glyph extends js.Object {
  def font: Font = js.native
  def name: String = js.native
  def unicode: Int = js.native
  def unicodes: js.Array[Int] = js.native
  def index: Int = js.native
  def advanceWidth: Double = js.native
  def xMin: Double = js.native
  def yMin: Double = js.native
  def xMax: Double = js.native
  def yMax: Double = js.native
  def path: Path = js.native

  def getPath(x: Double = js.native, y: Double = js.native, fontSize: Double = js.native): Path = js.native
  def getBoundingBox(): BoundingBox = js.native
  def draw(ctx: CanvasRenderingContext2D,
           x: Double = js.native,
           y: Double = js.native,
           fontSize: Double = js.native): Unit = js.native
  def drawPoints(ctx: CanvasRenderingContext2D,
                 x: Double = js.native,
                 y: Double = js.native,
                 fontSize: Double = js.native): Unit = js.native
  def drawMetrics(ctx: CanvasRenderingContext2D,
                  x: Double = js.native,
                  y: Double = js.native,
                  fontSize: Double = js.native): Unit = js.native
}