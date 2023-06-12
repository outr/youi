package com.outr

import org.scalajs.dom.CanvasRenderingContext2D

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object CanvgImplicits {
  implicit def context2CanvgContext(context: CanvasRenderingContext2D): CanvgContext = context.asInstanceOf[CanvgContext]
}

@js.native
@JSGlobal
class CanvgContext(context: CanvasRenderingContext2D) extends js.Object {
  def drawSvg(svg: String,
              x: Double,
              y: Double,
              width: Double,
              height: Double,
              options: CanvgOptions = js.native): Unit = js.native
}