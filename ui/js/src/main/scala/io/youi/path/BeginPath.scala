package io.youi.path

import io.youi.Context

object BeginPath extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.begin()

  override def toString: String = "BeginPath"
}
