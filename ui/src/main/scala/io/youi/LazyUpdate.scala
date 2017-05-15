package io.youi

import java.util.concurrent.atomic.AtomicBoolean

class LazyUpdate(f: () => Unit) {
  private val dirty = new AtomicBoolean(false)

  def flag(): Unit = dirty.set(true)
  def isFlagged: Boolean = dirty.get()

  def update(): Unit = if (dirty.compareAndSet(true, false)) {
    f()
  }
}

object LazyUpdate {
  def apply(f: => Unit): LazyUpdate = new LazyUpdate(() => f)
}