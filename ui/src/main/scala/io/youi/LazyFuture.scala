package io.youi

import java.util.concurrent.atomic.AtomicBoolean

import scala.concurrent.Future
import scala.concurrent.duration._

class LazyFuture[T](f: () => Future[T], maxFrequency: FiniteDuration) {
  private var lastUpdate: Long = 0L
  private val dirty = new AtomicBoolean(false)

  def flag(): Unit = dirty.set(true)
  def isFlagged: Boolean = dirty.get()
  def isReady: Boolean = System.currentTimeMillis() - lastUpdate >= maxFrequency.toMillis

  private var future: Option[Future[T]] = None

  def update(force: Boolean = false): Unit = {
    val finished = future.forall(_.isCompleted)
    if (finished) {
      if (future.nonEmpty) {
        future = None
        lastUpdate = System.currentTimeMillis()
      }

      if ((isReady && dirty.compareAndSet(true, false)) || force) {
        future = Some(f())
      }
    }
  }
}

object LazyFuture {
  def apply[T](f: => Future[T], maxFrequency: FiniteDuration = 0.millis): LazyFuture[T] = new LazyFuture(() => f, maxFrequency)
}