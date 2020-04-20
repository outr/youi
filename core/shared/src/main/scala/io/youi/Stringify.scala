package io.youi

trait Stringify[T] {
  def fromString(value: String): Option[T]
  def toString(value: T): Option[String]
}

object Stringify {
  def apply[T](to: T => Option[String])(from: String => Option[T]): Stringify[T] = {
    new Stringify[T] {
      override def fromString(value: String): Option[T] = from(value)

      override def toString(value: T): Option[String] = to(value)
    }
  }

  object Pixels extends Stringify[Double] {
    override def fromString(value: String): Option[Double] = try {
      Some(value.replaceAllLiterally("px", "").toDouble)
    } catch {
      case _: Throwable => None
    }

    override def toString(value: Double): Option[String] = Some(s"${value}px")
  }
}