package io.youi.component.mixins

import io.youi.drawable.Context
import io.youi.paint.{Paint, Stroke}
import io.youi.{Axis, Color}

trait ScrollBar {
  def draw(component: ScrollSupport, context: Context, axis: Axis, offset: Double, thickness: Double): Unit
}

object ScrollBar {
  object None extends ScrollBar {
    override def draw(component: ScrollSupport, context: Context, axis: Axis, offset: Double, thickness: Double): Unit = {}
  }
  def simple(stroke: Stroke = Stroke.none,
             fill: Paint = Color.Black,
             thickness: Double = 20.0): SimpleScrollBar = new SimpleScrollBar(stroke, fill, thickness)
}