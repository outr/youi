package io.youi

trait Drawable {
  def draw(context: Context): Unit
}

object Drawable {
  object None extends Drawable {
    override def draw(context: Context): Unit = {}
  }
}