package io.youi

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

class SingleThreadedFuture(errorHandler: Throwable => Unit = (t: Throwable) => scribe.error(t)) {
  private var future: Future[Any] = Future.successful(())

  def apply[R](f: => Future[R]): Future[R] = {
    if (future.isCompleted) {
      future = Future.successful(())
    }
    val promise = Promise[Unit]
    future.onComplete { _ =>
      promise.success(())
    }
    future.failed.foreach(errorHandler)
    val updated = promise.future.flatMap { _ =>
      f
    }
    future = updated
    updated
  }
}