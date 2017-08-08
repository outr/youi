package io.youi.drawable

trait Drawable {
  def update(width: Double, height: Double)(f: Context => Unit): Unit
}