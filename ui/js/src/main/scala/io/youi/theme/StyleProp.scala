package io.youi.theme

import reactify.{ChangeObserver, Observer, Val, Var}

class StyleProp[T](val name: String, parent: Var[Theme], default: => T) {
  val option: Var[Option[T]] = Var(None)
  val value: Val[T] = Val[T](option().orElse(parent.get.get[T](name).map(_.get)).getOrElse(default))

  def apply(): T = value()
  def get: T = apply()
  def :=(value: => T): Unit = option := Option(value)
  def set(value: => T): Unit = option := Option(value)
  def clear(): Unit = option := None

  def attach(f: T => Unit,
             priority: Double = Observer.Priority.Normal): Observer[T] = value.attach(f, priority)
  def observe(observer: Observer[T]): Observer[T] = value.observe(observer)
  def detach(observer: Observer[T]): Unit = value.detach(observer)
  def attachAndFire(f: T => Unit): Observer[T] = value.attachAndFire(f)
  def changes(observer: ChangeObserver[T]): Observer[T] = value.changes(observer)
}
