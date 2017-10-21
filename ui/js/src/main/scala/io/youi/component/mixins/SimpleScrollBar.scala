package io.youi.component.mixins

import io.youi.Axis
import io.youi.drawable.Context
import io.youi.paint.{Paint, Stroke}

class SimpleScrollBar(stroke: Stroke, fill: Paint, size: Double) extends ScrollBar {
  override def draw(component: ScrollSupport, context: Context, axis: Axis, offset: Double, thickness: Double): Unit = {
    context.begin()
    axis match {
      case Axis.Horizontal => context.rect(offset, component.size.height - size - 5.0, thickness, size)
      case Axis.Vertical => context.rect(component.size.width - size - 5.0, offset, size, thickness)
    }
    context.close()
    context.fill(fill, apply = true)
    context.stroke(stroke, apply = true)
  }
}
