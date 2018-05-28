package io.youi.theme

import io.youi.MapStore
import reactify._

trait Theme extends StringifyImplicits {
  protected def defaultParentTheme: Theme

  lazy val parentTheme: Var[Theme] = Var(defaultParentTheme)

  private val store = new MapStore

  private[theme] def get[T](name: String): Option[Var[T]] = if (this == Theme) {
    store.get[Var[T]](name)
  } else {
    store.get[Var[T]](name).orElse(parentTheme().get[T](name))
  }

  protected def updateTransform(): Unit = {}
  protected def updateRendering(): Unit = {}

  protected[youi] def style[T](name: String,
                               default: => T,
                               connect: Option[StyleConnect[T]],
                               updatesTransform: Boolean = false,
                               updatesRendering: Boolean = false,
                               ignoreParent: Boolean = false): Var[T] = {
    val v = Var[T](if (ignoreParent) {
      default
    } else {
      parentTheme().get[T](name).map(_.get).getOrElse(default)
    })
    if (name == "type") {
      v.attachAndFire { value =>
        scribe.info(s"Value: $value, ${parentTheme().get[Any]("type")}")
      }
    }
    store(name) = v
    connect.foreach(_.init(this, v, name))
    if (updatesTransform || updatesRendering) {
      v.attach { _ =>
        if (updatesTransform) {
          updateTransform()
        }
        if (updatesRendering) {
          updateRendering()
        }
      }
    }
    v
  }
}

object Theme extends Theme {
  override protected def defaultParentTheme: Theme = Theme
}