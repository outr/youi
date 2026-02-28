package youi.storage

import rapid.Task

trait StorageImplementation {
  def get(key: String): Task[Option[String]]
  def set(key: String, value: String): Task[Unit]
  def remove(key: String): Task[Unit]
  def clear(): Task[Unit]
}
