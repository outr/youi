package io.youi.storage

import scala.concurrent.Future

trait StorageImplementation {
  def get(key: String): Future[Option[String]]
  def set(key: String, value: String): Future[Unit]
  def remove(key: String): Future[Unit]
  def clear(): Future[Unit]
}