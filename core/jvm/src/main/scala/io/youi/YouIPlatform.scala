package io.youi

import java.text.SimpleDateFormat
import java.util.Locale

object YouIPlatform {
  def parseHTTPDate(date: String): Option[Long] = {
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

  def toHTTPDate(time: Long): String = {
    val parser = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss zzz", Locale.ENGLISH)
    parser.format(time)
  }
}
