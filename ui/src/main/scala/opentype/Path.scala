package opentype

import org.scalajs.dom.CanvasRenderingContext2D

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("opentype.Path")
class Path extends js.Object {
  var commands: js.Dynamic = js.native
  var fill: String = js.native
  var stroke: String = js.native
  var strokeWidth: Double = js.native
  def draw(ctx: CanvasRenderingContext2D): Unit = js.native
  def getBoundingBox(): BoundingBox = js.native
  def toPathData(decimalPlaces: Int = js.native): String = js.native
  def toSVG(decimalPlaces: Int = js.native): String = js.native
}