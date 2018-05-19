package io.youi.theme

import io.youi.MapStore
import reactify._

trait Theme {
  lazy val parentTheme: Var[Option[Theme]] = Var(None)

  private val store = new MapStore

  private def get[T](name: String): Option[Var[T]] = store.get[Var[T]](name).orElse(parentTheme().flatMap(_.get[T](name)))

  protected def style[T](name: String, default: => T, connect: Option[StyleConnect[T]]): Var[T] = {
    val v = Var[T](parentTheme().flatMap(_.get[T](name)).map(_.get).getOrElse(default))
    store(name) = v
    connect.foreach(_.init(this, v, name))
    v
  }
}