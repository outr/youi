//package io.youi.paint
//
//import io.youi.Context
//import org.scalajs.dom.CanvasPattern
//import org.scalajs.dom.raw.CanvasRenderingContext2D
//
//import scala.scalajs.js
//
//class ComponentPaint[C <: Component](component: C, repetition: Repetition) extends PatternPaint {
//  override def modified: Long = component.modified()
//
//  override def update(delta: Double): Unit = {
//    super.update(delta: Double)
//
//    component.update(delta)
//  }
//
//  override def asJS(context: Context): js.Any = if (component.drawer.context.canvas.width > 0 && component.drawer.context.canvas.height > 0) {
//    super.asJS(context)
//  } else {
//    ""
//  }
//
//  override def createPattern(context: CanvasRenderingContext2D): CanvasPattern = {
//    context.createPattern(component.drawer.context.canvas, repetition.value)
//  }
//}
