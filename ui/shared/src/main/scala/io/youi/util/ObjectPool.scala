package io.youi.util

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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

  protected def future[R](f: T => Future[R]): Future[R] = {
    val t = apply()
    val fut = f(t)
    fut.onComplete { _ =>
      restore(t)
    }
    fut
  }

  def instances: Int = created
  def available: Int = cached.length
}

object ObjectPool {
  def apply[T](createFunction: => T): PublicObjectPool[T] = new PublicObjectPool[T](() => createFunction)
}

class PublicObjectPool[T](createFunction: () => T) extends ObjectPool[T] {
  override def get: Option[T] = super.get

  override def apply(): T = super.apply()

  override def restore(t: T): Unit = super.restore(t)

  override def use[R](f: (T) => R): R = super.use(f)

  override def future[R](f: (T) => Future[R]): Future[R] = super.future(f)

  override protected def create(): T = createFunction()
}