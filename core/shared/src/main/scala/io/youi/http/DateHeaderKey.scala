package io.youi.http

import java.text.SimpleDateFormat
import java.util.{Date, Locale}

class DateHeaderKey(val key: String, val commaSeparated: Boolean = false) extends TypedHeaderKey[Long] {
  import DateHeaderKey._

  override def value(headers: Headers): Option[Long] = get(headers).map(parser.parse(_).getTime)

  override def apply(date: Long): Header = Header(this, parser.format(new Date(date)))
}

object DateHeaderKey {
  def parser: SimpleDateFormat = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
}