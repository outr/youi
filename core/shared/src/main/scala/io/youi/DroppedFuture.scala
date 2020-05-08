package io.youi

import scala.concurrent.{ExecutionContext, Future}

object DroppedFuture {
  var errorHandler: Throwable => Unit = (t: Throwable) => scribe.error(t)

  var enabled: Boolean = true

  private var running = Map.empty[Any, Ref]

  def apply(key: Any, value: String)
           (f: => Future[Unit])
           (implicit ec: ExecutionContext): Future[Unit] = if (enabled) {
    synchronized {
      val ref = running.get(key) match {
        case Some(r) if (!r.future.isCompleted) => if (r.value == value) {      // Already running this value, ignore
          r
        } else {
          r.copy(next = Some((value, () => f)))
        }
        case _ => {
          val future = f.map { _ =>
            finished(key)
          }
          Ref(key, value, future, next = None)
        }
      }
      running += key -> ref
      ref.future
    }
  } else {
    f
  }

  private def finished(key: Any)(implicit ec: ExecutionContext): Unit = synchronized {
    running.get(key) match {
      case Some(ref) if ref.next.nonEmpty => {
        val (value, ff) = ref.next.get
        running -= key
        apply(key, value)(ff())
      }
      case _ => running -= key
    }
  }

  case class Ref(key: Any, value: String, future: Future[Unit], next: Option[(String, () => Future[Unit])])
}