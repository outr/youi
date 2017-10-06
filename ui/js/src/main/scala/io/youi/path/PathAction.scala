package io.youi.path

import io.youi.{Context, Drawable}

trait PathAction extends Drawable {
  override final def draw(context: Context, x: Double, y: Double): Unit = draw(context, x, y, 1.0, 1.0)

  def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit
}