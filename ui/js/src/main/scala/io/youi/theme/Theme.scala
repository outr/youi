package io.youi.theme

import io.youi.MapStore
import reactify._

trait Theme extends StringifyImplicits {
  protected def defaultParentTheme: Theme

  lazy val parentTheme: Var[Theme] = Var(defaultParentTheme)

  private val store = new MapStore

  private[theme] def get[T](name: String): Option[StyleProp[T]] = if (this == Theme) {
    store.get[StyleProp[T]](name)
  } else {
    store.get[StyleProp[T]](name).orElse(parentTheme().get[T](name))
  }

  protected def updateTransform(): Unit = {}
  protected def updateRendering(): Unit = {}

  protected[youi] def style[T](name: String,
                               default: => T,
                               connect: Option[StyleConnect[T]],
                               updatesTransform: Boolean = false,
                               updatesRendering: Boolean = false,
                               ignoreParent: Boolean = false): StyleProp[T] = {
    val prop = new StyleProp[T](name, parentTheme, default)
    store(name) = prop
    connect.foreach(_.init(this, prop, name))
    if (updatesTransform || updatesRendering) {
      prop.attach { _ =>
        if (updatesTransform) {
          updateTransform()
        }
        if (updatesRendering) {
          updateRendering()
        }
      }
    }
    prop
  }
}

object Theme extends Theme {
  override protected def defaultParentTheme: Theme = Theme
}

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