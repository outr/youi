package youi.storage

import rapid.Task
import reactify.Var
import fabric.io.{Format, JsonFormatter, JsonParser}
import fabric.rw._

trait Storage {
  def implementation: StorageImplementation

  object string {
    def get(key: String): Task[Option[String]] = implementation.get(key)
    def getOrElse(key: String, default: => String): Task[String] = get(key).map(_.getOrElse(default))
    def update(key: String, value: String): Task[Unit] = implementation.set(key, value)
    def remove(key: String): Task[Unit] = implementation.remove(key)
    def prop(key: String, default: => String): Var[String] = {
      val p = Var(default)
      get(key).map(_.map(p.static)).startUnit()
      p.attach { v =>
        update(key, v)
      }
      p
    }
  }

  def get[T: RW](key: String): Task[Option[T]] = string.get(key).map(_.map(s => JsonParser(s, Format.Json)).map(_.as[T]))
  def getOrElse[T: RW](key: String, default: => T): Task[T] = get[T](key).map(_.getOrElse(default))
  def getOrCreate[T: RW](key: String, default: => T): Task[T] = get[T](key).flatMap {
    case Some(value) => Task.pure(value)
    case None =>
      val value: T = default
      update[T](key, value).map(_ => value)
  }
  def update[T: RW](key: String, value: T): Task[Unit] = string.update(key, JsonFormatter.Default(value.json))
  def remove(key: String): Task[Unit] = string.remove(key)

  def prop[T: RW](key: String, default: => T): Var[T] = {
    val p = Var[T](default)
    connect[T](key, p).startUnit()
    p
  }

  def connect[T: RW](key: String, prop: Var[T]): Task[Option[T]] = get[T](key).map {
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
