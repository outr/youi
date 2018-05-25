package io.youi.theme

import io.youi.MapStore
import reactify._

trait Theme extends StringifyImplicits {
  lazy val parentTheme: Var[Option[Theme]] = Var(None)

  private val store = new MapStore

  private def get[T](name: String): Option[Var[T]] = store.get[Var[T]](name).orElse(parentTheme().flatMap(_.get[T](name)))

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
      parentTheme().flatMap(_.get[T](name)).map(_.get).getOrElse(default)
    })
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