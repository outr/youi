package io.youi.http

import scala.util.Try

class LongHeaderKey(val key: String, val commaSeparated: Boolean = true) extends TypedHeaderKey[Long] {
  override def value(headers: Headers): Option[Long] = Try(headers.first(this).map(_.toLong)).getOrElse(None)

  override def apply(value: Long): Header = Header(this, value.toString)
}
