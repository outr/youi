package io.youi.storage

import cats.effect.IO
import org.scalajs.dom

class JavaScriptStorageImplementation(storage: dom.Storage) extends StorageImplementation {
  override def get(key: String): IO[Option[String]] = IO.pure(Option(storage.getItem(key)))

  override def set(key: String, value: String): IO[Unit] = IO.pure(storage.setItem(key, value))

  override def remove(key: String): IO[Unit] = IO.pure(storage.removeItem(key))

  override def clear(): IO[Unit] = IO.pure(storage.clear())
}