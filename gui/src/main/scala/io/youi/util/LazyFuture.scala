package io.youi.util

import java.util.concurrent.atomic.AtomicBoolean

import io.youi.BackgroundUpdates
import reactify.Var

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Future, Promise}

class LazyFuture[T](f: () => Future[T], maxFrequency: FiniteDuration, automatic: Boolean = true) {
  private val lastUpdate = Var(0L)
  private val dirty = new AtomicBoolean(false)

  def flag(): Future[Unit] = {
    if (dirty.compareAndSet(false, true)) {
      if (automatic) {
        if (isReady) {
          update()
        } else {
          BackgroundUpdates.once(delay.millis)(update())
        }
      }
    }
    val p = Promise[Unit]
    lastUpdate.once(_ => p.success(()))
    p.future
  }
  def isFlagged: Boolean = dirty.get()
  def elapsed: Long = System.currentTimeMillis() - lastUpdate
  def delay: Long = math.max(0L, maxFrequency.toMillis - elapsed)
  def isReady: Boolean = delay == 0L

  private var future: Option[Future[T]] = None

  def update(force: Boolean = false): Unit = {
    val finished = future.forall(_.isCompleted)
    if (finished) {
      if (future.nonEmpty) {
        future = None
        lastUpdate @= System.currentTimeMillis()
      }

      if ((isReady && dirty.compareAndSet(true, false)) || force) {
        val future = f()
        future.failed.foreach(t => scribe.error(t))
        if (automatic) {
          future.onComplete(_ => update())
        }
        this.future = Some(future)
      }
    }
  }
}

object LazyFuture {
  def apply[T](f: => Future[T],
               maxFrequency: FiniteDuration = 0.millis,
               automatic: Boolean = true): LazyFuture[T] = new LazyFuture(() => f, maxFrequency, automatic)
}