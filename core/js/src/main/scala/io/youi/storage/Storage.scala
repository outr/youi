package io.youi.storage

import cats.effect.{IO, Sync, SyncIO}
import reactify.Var

import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import cats.effect.unsafe.implicits.global
import fabric._
import fabric.parse.JsonParser
import fabric.rw._

trait Storage {
  def implementation: StorageImplementation

  object string {
    def get(key: String): IO[Option[String]] = implementation.get(key)
    def getOrElse(key: String, default: => String): IO[String] = get(key).map(_.getOrElse(default))
    def update(key: String, value: String): IO[Unit] = implementation.set(key, value)
    def remove(key: String): IO[Unit] = implementation.remove(key)
    def prop(key: String, default: => String): Var[String] = {
      val p = Var(default)
      get(key).map(_.map(p.static)).unsafeRunAndForget()
      p.attach { v =>
        update(key, v)
      }
      p
    }
  }

  def get[T: Writer](key: String): IO[Option[T]] = string.get(key).map(_.map(JsonParser.parse).map(_.as[T]))
  def getOrElse[T: Writer](key: String, default: => T): IO[T] = get[T](key).map(_.getOrElse(default))
  def getOrCreate[T: ReaderWriter](key: String, default: => T): IO[T] = get[T](key).flatMap {
    case Some(value) => IO.pure(value)
    case None =>
      val value: T = default
      update[T](key, value).map(_ => value)
  }
  def update[T: Reader](key: String, value: T): IO[Unit] = string.update(key, JsonParser.format(value.json))
  def remove(key: String): IO[Unit] = string.remove(key)

  def prop[T: ReaderWriter](key: String, default: => T): Var[T] = {
    val p = Var[T](default)
    connect[T](key, p).unsafeRunAndForget()
    p
  }

  def connect[T: ReaderWriter](key: String, prop: Var[T]): IO[Option[T]] = get[T](key).map {
    case Some(value) =>
      prop @= value
      Some(value)
    case None =>
      None
  }.map { v =>
    prop.attach { value =>
      update[T](key, value)
    }
    v
  }
}