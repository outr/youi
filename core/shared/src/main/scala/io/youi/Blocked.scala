package io.youi

import scala.concurrent.{Future, Promise}
import scribe.Execution.global

/**
  * Allows blocking on a key for sequential futures
  */
object Blocked {
  var errorHandler: Throwable => Unit = (t: Throwable) => scribe.error(t)

  var enabled: Boolean = true

  private var map = Map.empty[Any, Future[_]]

  def apply[Result](keys: Any*)(f: => Future[Result]): Future[Result] = if (enabled) {
    synchronized {
      val previous = Future.sequence(keys.map(map.getOrElse(_, Future.successful(()))))
      val future = if (previous.isCompleted) {
        f
      } else {
        previous.transformWith { t =>
          t.failed.foreach(errorHandler)
          f
        }
      }
      future.onComplete { _ =>
        Blocked.synchronized {
          keys.foreach { key =>
            if (map(key) eq future) {
              map -= key
            }
          }
        }
      }

      Blocked.synchronized {
        keys.foreach { key =>
          map += key -> future
        }
      }
      future
    }
  } else {
    f
  }
}