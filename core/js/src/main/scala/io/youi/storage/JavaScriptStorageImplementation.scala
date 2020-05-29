package io.youi.storage

import org.scalajs.dom.raw

import scala.concurrent.Future

class JavaScriptStorageImplementation(storage: raw.Storage) extends StorageImplementation {
  override def get(key: String): Future[Option[String]] = Future.successful(Option(storage.getItem(key)))

  override def set(key: String, value: String): Future[Unit] = Future.successful(storage.setItem(key, value))

  override def remove(key: String): Future[Unit] = Future.successful(storage.removeItem(key))

  override def clear(): Future[Unit] = Future.successful(storage.clear())
}