package io.youi.http

class StringHeaderKey(val key: String, val commaSeparated: Boolean = true) extends TypedHeaderKey[String] {
  override def value(headers: Headers): Option[String] = get(headers)

  override def apply(value: String): Header = Header(this, value)
}
