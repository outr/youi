package io.youi

import java.util.concurrent.atomic.AtomicBoolean

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class LazyFuture[T](f: () => Future[T], maxFrequency: FiniteDuration) {
  private var lastUpdate: Long = 0L
  private val dirty = new AtomicBoolean(false)

  def flag(): Unit = if (dirty.compareAndSet(false, true)) {
    if (isReady) {
      AnimationFrame.once(10.millis)(update())
    } else {
      AnimationFrame.once(delay.millis)(update())
    }
  }
  def isFlagged: Boolean = dirty.get()
  def elapsed: Long = System.currentTimeMillis() - lastUpdate
  def delay: Long = math.max(0L, maxFrequency.toMillis - elapsed)
  def isReady: Boolean = delay == 0L

  private var future: Option[Future[T]] = None

  private def update(force: Boolean = false): Unit = {
    val finished = future.forall(_.isCompleted)
    if (finished) {
      if (future.nonEmpty) {
        future = None
        lastUpdate = System.currentTimeMillis()
      }

      if ((isReady && dirty.compareAndSet(true, false)) || force) {
        val future = f()
        future.onComplete(_ => update())
        this.future = Some(future)
      }
    }
  }
}

object LazyFuture {
  def apply[T](f: => Future[T], maxFrequency: FiniteDuration = 0.millis): LazyFuture[T] = new LazyFuture(() => f, maxFrequency)
}