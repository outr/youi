package io.youi.storage

import cats.effect.IO

trait StorageImplementation {
  def get(key: String): IO[Option[String]]
  def set(key: String, value: String): IO[Unit]
  def remove(key: String): IO[Unit]
  def clear(): IO[Unit]
}