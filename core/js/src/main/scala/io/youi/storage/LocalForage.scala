package io.youi.storage

import rapid.Task
import rapid.task.Completable

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.util.{Failure, Success}

object LocalForage extends Storage {
  private def fromThenable[T](thenable: js.Thenable[T]): Task[T] = {
    import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
    val c: Completable[T] = Task.completable[T]
    thenable.toFuture.onComplete {
      case Success(v) => c.success(v)
      case Failure(e) => c.failure(e)
    }
    c
  }

  override def implementation: StorageImplementation = new StorageImplementation {
    override def get(key: String): Task[Option[String]] =
      fromThenable(JavaScriptLocalForage.getItem(key)).map(Option.apply)
    override def set(key: String, value: String): Task[Unit] =
      fromThenable(JavaScriptLocalForage.setItem(key, value)).map(_ => ())
    override def remove(key: String): Task[Unit] =
      fromThenable(JavaScriptLocalForage.removeItem(key))
    override def clear(): Task[Unit] =
      fromThenable(JavaScriptLocalForage.clear())
  }
}
