package io.youi.http

class DateHeaderKey(val key: String, val commaSeparated: Boolean = false) extends TypedHeaderKey[Long] {
  import DateHeaderKey._

  override def value(headers: Headers): Option[Long] = get(headers).flatMap(parse)

  override def apply(date: Long): Header = Header(this, format(date))
}

object DateHeaderKey {
  def parse(date: String): Option[Long] = io.youi.YouIPlatform.parseHTTPDate(date)

  def format(date: Long): String = io.youi.YouIPlatform.toHTTPDate(date)
}