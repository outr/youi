package io.youi.processor

import scala.concurrent.duration._

trait ProcessorMonitor {
  def monitor(name: String,
              processed: Long,
              total: Long,
              elapsed: Double,
              perSecond: Double,
              threads: Int,
              finished: Boolean): Unit

  def resolution: FiniteDuration
}

object ProcessorMonitor {
  lazy val Default: ProcessorMonitor = Logging()

  case object Void extends ProcessorMonitor {
    override def monitor(name: String,
                         processed: Long,
                         total: Long,
                         elapsed: Double,
                         perSecond: Double,
                         threads: Int,
                         finished: Boolean): Unit = {}

    override def resolution: FiniteDuration = 1.hour
  }

  case class Logging(resolution: FiniteDuration = 5.seconds) extends ProcessorMonitor {
    override def monitor(name: String,
                         processed: Long,
                         total: Long,
                         elapsed: Double,
                         perSecond: Double,
                         threads: Int, finished: Boolean): Unit = if (finished) {
      scribe.info(s"$name: Processed $processed of $total in $elapsed ($perSecond per second in $threads threads)")
    } else {
      scribe.info(s"$name: Finished Processing $processed of $total in $elapsed ($perSecond per second in $threads threads)")
    }
  }
}