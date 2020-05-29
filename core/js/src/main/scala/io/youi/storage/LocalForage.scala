package io.youi.storage

import scala.concurrent.Future

import scribe.Execution.global

object LocalForage extends Storage {
  override def implementation: StorageImplementation = new StorageImplementation {
    override def get(key: String): Future[Option[String]] = JavaScriptLocalForage.getItem(key).toFuture.map(Option.apply)
    override def set(key: String, value: String): Future[Unit] = JavaScriptLocalForage.setItem(key, value).toFuture.map(_ => ())
    override def remove(key: String): Future[Unit] = JavaScriptLocalForage.removeItem(key).toFuture
    override def clear(): Future[Unit] = JavaScriptLocalForage.clear().toFuture
  }
}