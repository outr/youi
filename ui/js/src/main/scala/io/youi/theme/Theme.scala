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

  protected def invalidateTransform(): Unit = {}
  protected def invalidateRendering(): Unit = {}

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
      prop.attachAndFire { _ =>
        if (updatesTransform) {
          invalidateTransform()
        }
        if (updatesRendering) {
          invalidateRendering()
        }
      }
    }
    prop
  }
}

object Theme extends Theme {
  override protected def defaultParentTheme: Theme = Theme
}