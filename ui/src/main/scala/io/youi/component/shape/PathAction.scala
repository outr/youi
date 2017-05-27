package io.youi.component.shape

import org.scalajs.dom.raw.CanvasRenderingContext2D

trait PathAction {
  def invoke(context: CanvasRenderingContext2D): Unit
}