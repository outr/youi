package io.youi

import java.util.concurrent.atomic.AtomicBoolean

import scala.concurrent.duration._

class LazyUpdate(f: () => Unit, maxFrequency: FiniteDuration) {
  private var lastUpdate: Long = 0L
  private val dirty = new AtomicBoolean(false)

  def flag(): Unit = dirty.set(true)
  def isFlagged: Boolean = dirty.get()
  def isReady: Boolean = System.currentTimeMillis() - lastUpdate >= maxFrequency.toMillis

  def update(force: Boolean = false): Unit = if ((isReady && dirty.compareAndSet(true, false)) || force) {
    try {
      f()
    } finally {
      lastUpdate = System.currentTimeMillis()
    }
  }
}

object LazyUpdate {
  def apply(f: => Unit, maxFrequency: FiniteDuration = 0.millis): LazyUpdate = new LazyUpdate(() => f, maxFrequency)
}