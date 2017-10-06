package io.youi.path

import io.youi.Context

object ClosePath extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.close()
}
