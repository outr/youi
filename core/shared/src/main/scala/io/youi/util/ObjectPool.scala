package io.youi.util

import cats.effect.IO

trait ObjectPool[T] {
  private var created = 0
  private var cached = List.empty[T]

  protected def get: Option[T] = if (cached.nonEmpty) {
    try {
      cached.headOption
    } finally {
      cached = cached.tail
    }
  } else {
    None
  }

  protected def apply(): T = get.getOrElse {
    created += 1
    create()
  }

  protected def create(): T

  protected def restore(t: T): Unit = cached = t :: cached

  protected def use[R](f: T => R): R = {
    val t = apply()
    try {
      f(t)
    } finally {
      restore(t)
    }
  }

  protected def io[R](f: T => IO[R]): IO[R] = {
    IO(apply()).flatMap { value =>
      IO(f(value)).flatten.guarantee(IO {
        restore(value)
      })
    }
  }

  def instances: Int = created
  def available: Int = cached.length
}

object ObjectPool {
  def apply[T](createFunction: => T): PublicObjectPool[T] = new PublicObjectPool[T](() => createFunction)
}

class PublicObjectPool[T](createFunction: () => T) extends ObjectPool[T] {
  override protected def create(): T = createFunction()
}