package io.youi.storage

import rapid.Task
import org.scalajs.dom

class JavaScriptStorageImplementation(storage: dom.Storage) extends StorageImplementation {
  override def get(key: String): Task[Option[String]] = Task.pure(Option(storage.getItem(key)))

  override def set(key: String, value: String): Task[Unit] = Task.pure(storage.setItem(key, value))

  override def remove(key: String): Task[Unit] = Task.pure(storage.removeItem(key))

  override def clear(): Task[Unit] = Task.pure(storage.clear())
}
