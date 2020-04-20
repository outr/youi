package io.youi.theme

class StyleProp[T](val name: String, parent: Var[Theme], default: => T) {
  val option: Var[Option[T]] = Var(None)
  val value: Val[T] = Val[T](option().orElse(parent.get.get[T](name).map(_.get)).getOrElse(default))

  def apply(): T = value()
  def get: T = apply()
  def :=(value: => T): Unit = option := Option(value)
  def @=(value: T): Unit = option @= Option(value)
  def set(value: => T): Unit = option := Option(value)
  def clear(): Unit = option @= None

  def attach(f: T => Unit,
             priority: Double = Priority.Normal): Reaction[T] = value.attach(f, priority)
  def observe(reaction: Reaction[T]): Reaction[T] = value.reactions += reaction
  def detach(reaction: Reaction[T]): Unit = value.reactions -= reaction
  def attachAndFire(f: T => Unit): Reaction[T] = value.attachAndFire(f)
  def changes(f: (T, T) => Unit, priority: Double = Priority.Normal): Reaction[T] = value.changes(f, priority)
}
