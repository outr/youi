package io.youi

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scribe.LogRecord.Stringify._

class SingleThreadedFuture(errorHandler: Throwable => Unit = (t: Throwable) => scribe.error(t)) {
  private var future: Future[Any] = Future.successful(())

  def apply[R](f: => Future[R]): Future[R] = {
    val promise = Promise[Unit]
    future.failed.foreach(errorHandler)
    val updated = promise.future.flatMap { _ =>
      f
    }
    if (future.isCompleted) {
      promise.success(())
    } else {
      future.onComplete { _ =>
        promise.success(())
      }
    }
    future = updated
    updated
  }
}