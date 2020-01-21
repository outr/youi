package io.youi

import scala.concurrent.Future
import scala.util.{Failure, Success}

import scribe.Execution.global

/**
  * KeyedSequence allows the use of a unique key to build sequences of futures so that key is not concurrently utilized.
  *
  * Should self-clean upon completion.
  */
object KeyedSequence {
  var errorHandler: Throwable => Unit = (t: Throwable) => scribe.error(t)

  private var map = Map.empty[Any, Future[Any]]

  def apply[Key, Return](key: Key)(builder: => Future[Return]): Future[Return] = synchronized {
    val future: Future[Return] = map.get(key) match {
      case Some(running) => {
        running.transformWith { _ =>
          builder
        }
      }
      case None => {
        builder
      }
    }
    map += key -> future
    future.onComplete { result =>
      KeyedSequence.synchronized {
        if (map(key) eq future) {
          map -= key
        }
        result match {
          case Success(_) => // Success, nothing to do
          case Failure(exception) => errorHandler(exception)
        }
      }
    }
    future
  }
}