package io.youi

import scala.concurrent.Future
import scribe.Execution.global

object Blocked {
  private var map = Map.empty[Any, Future[_]]

  def apply[Result](keys: Any*)(f: => Future[Result]): Future[Result] = synchronized {
    val previous = Future.sequence(keys.map(map.getOrElse(_, Future.successful(()))))

    val future = previous.flatMap { _ =>
      f
    }
    keys.foreach { key =>
      map += key -> future
    }
    future
  }
}