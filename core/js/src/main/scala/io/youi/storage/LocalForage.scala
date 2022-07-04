package io.youi.storage

import cats.effect.IO

object LocalForage extends Storage {
  override def implementation: StorageImplementation = new StorageImplementation {
    override def get(key: String): IO[Option[String]] =
      IO.fromThenable(IO.pure(JavaScriptLocalForage.getItem(key))).map(Option.apply)
    override def set(key: String, value: String): IO[Unit] =
      IO.fromThenable(IO.pure(JavaScriptLocalForage.setItem(key, value))).map(_ => ())
    override def remove(key: String): IO[Unit] =
      IO.fromThenable(IO.pure(JavaScriptLocalForage.removeItem(key)))
    override def clear(): IO[Unit] =
      IO.fromThenable(IO.pure(JavaScriptLocalForage.clear()))
  }
}