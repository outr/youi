package io.youi.paint

import io.youi.component.Component
import org.scalajs.dom.CanvasPattern
import org.scalajs.dom.raw.CanvasRenderingContext2D

class ComponentPaint(component: Component, repetition: Repetition) extends PatternPaint {
  override def createPattern(context: CanvasRenderingContext2D): CanvasPattern = {
    context.createPattern(component.drawer.canvas, repetition.value)
  }
}
