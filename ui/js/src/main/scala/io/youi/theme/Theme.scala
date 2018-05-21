package io.youi.theme

import io.youi.{Cursor, MapStore}
import io.youi.paint.Paint
import io.youi.style.Length
import reactify._

trait Theme {
  implicit def stringifyLength: Stringify[Length] = Length
  implicit def stringifyPaint: Stringify[Paint] = Paint
  implicit def stringifyCursor: Stringify[Cursor] = Cursor
  implicit def stringifyBoolean: Stringify[Boolean] = Stringify[Boolean](b => Some(b.toString)) {
    case "true" => Some(true)
    case "false" => Some(false)
    case _ => None
  }
  implicit def stringifyString: Stringify[String] = Stringify[String](Some(_))(Some(_))
  implicit def stringifyDouble: Stringify[Double] = Stringify[Double](d => Some(d.toString)) { s => try {
    Some(s.toDouble)
  } catch {
    case _: Throwable => None
  }}

  lazy val parentTheme: Var[Option[Theme]] = Var(None)

  private val store = new MapStore

  private def get[T](name: String): Option[Var[T]] = store.get[Var[T]](name).orElse(parentTheme().flatMap(_.get[T](name)))

  protected def updateTransform(): Unit = {}
  protected def updateRendering(): Unit = {}

  protected def style[T](name: String,
                         default: => T,
                         connect: Option[StyleConnect[T]],
                         updatesTransform: Boolean = false,
                         updatesRendering: Boolean = false): Var[T] = {
    val v = Var[T](parentTheme().flatMap(_.get[T](name)).map(_.get).getOrElse(default))
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