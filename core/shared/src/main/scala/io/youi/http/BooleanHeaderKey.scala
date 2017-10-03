package io.youi.http

class BooleanHeaderKey(val key: String, val commaSeparated: Boolean = true) extends TypedHeaderKey[Boolean] {
  override def value(headers: Headers): Option[Boolean] = get(headers).map(_.toBoolean)

  override def apply(value: Boolean): Header = Header(this, value.toString)
}
