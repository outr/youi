package io.youi

import reactify.Var

trait Drawable {
  lazy val modified: Var[Long] = Var(System.currentTimeMillis())
  def draw(context: Context): Unit
}

object Drawable {
  object None extends Drawable {
    override def draw(context: Context): Unit = {}
  }
}