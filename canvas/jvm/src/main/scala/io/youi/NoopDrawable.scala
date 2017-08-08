package io.youi

import io.youi.drawable.{Context, Drawable}

object NoopDrawable extends Drawable with Context {
  override def update(width: Double, height: Double)(f: (Context) => Unit): Unit = {
    f(this)
  }
}