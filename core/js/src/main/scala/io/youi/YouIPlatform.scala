package io.youi

import org.scalajs.dom._
import perfolation._

import scala.concurrent.{Future, Promise}
import scala.scalajs.js.Date

object YouIPlatform {
  private val Regex = """(.{3}), (.{2}) (.{3}) (.{4}) (.{2}):(.{2}):(.{2}) GMT""".r
  private val DayNames = Vector("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
  private val MonthNames = Vector("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

  def delay(millis: Long): Future[Unit] = {
    val promise = Promise[Unit]()
    window.setTimeout(() => {
      promise.success(())
    }, millis.toDouble)
    promise.future
  }

  def parseHTTPDate(date: String): Option[Long] = date match {
    case Regex(_, day, month, year, hour, minute, second) => {
      val date = Date.parse(s"$day $month $year $hour:$minute:$second GMT")
      Some(date.toLong)
    }
  }

  def toHTTPDate(time: Long): String = {
    val date = new Date(time.toDouble)
    s"${DayNames(date.getUTCDay().toInt)}, ${date.getUTCDate().f(i = 2)} ${MonthNames(date.getUTCMonth().toInt)} ${date.getUTCFullYear()} ${date.getUTCHours()}:${date.getUTCMinutes().f(i = 2)}:${date.getUTCSeconds().f(i = 2)} GMT"
  }
}