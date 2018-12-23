package io.youi

import scala.concurrent.{Future, Promise}
import scribe.Execution.global

object Blocked {
  private var map = Map.empty[Any, Future[_]]

  def apply[Result](keys: Any*)(f: => Future[Result]): Future[Result] = synchronized {
    val previous = Future.sequence(keys.map(map.getOrElse(_, Future.successful(()))))
    val future = if (previous.isCompleted) {
      f
    } else {
      val promise = Promise[Result]
      previous.onComplete { _ =>
        val next: Future[Result] = f
        next.onComplete(promise.complete)
      }
      promise.future
    }

    keys.foreach { key =>
      map += key -> future
    }
    future
  }
}