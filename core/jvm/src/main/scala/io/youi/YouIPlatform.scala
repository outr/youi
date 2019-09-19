package io.youi

import java.text.SimpleDateFormat
import java.util.{Locale, Timer, TimerTask}

import scala.concurrent.{Future, Promise}

object YouIPlatform {
  private lazy val timer = new Timer("io.youi.Time", true)

  def delay(millis: Long): Future[Unit] = {
    val promise = Promise[Unit]
    timer.schedule(new TimerTask {
      override def run(): Unit = promise.success(())
    }, millis)
    promise.future
  }

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
