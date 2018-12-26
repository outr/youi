package io.youi.http

import java.text.SimpleDateFormat
import java.util.Locale

class DateHeaderKey(val key: String, val commaSeparated: Boolean = false) extends TypedHeaderKey[Long] {
  import DateHeaderKey._

  override def value(headers: Headers): Option[Long] = get(headers).flatMap(parse)

  override def apply(date: Long): Header = Header(this, format(date))
}

object DateHeaderKey {
  def parse(date: String): Option[Long] = {
    val parser = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
    try {
      Some(parser.parse(date.replace('-', ' ')).getTime)
    } catch {
      case t: Throwable => {
        scribe.warn(s"Unable to parse date header: $date (${t.getMessage})")
        None
      }
    }
  }

  def format(date: Long): String = {
    val parser = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
    parser.format(date)
  }
}