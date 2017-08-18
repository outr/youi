package io.youi.path

import org.scalajs.dom.raw.CanvasRenderingContext2D

object BeginPath extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.beginPath()
}
