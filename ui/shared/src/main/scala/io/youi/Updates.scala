package io.youi

import reactify._

import scala.concurrent.duration._

trait Updates extends Updatable {
  lazy val delta: Channel[Double] = Channel[Double]

  def nextFrame(f: => Unit): Unit = delta.once(_ => f)

  override def update(delta: Double): Unit = this.delta := delta

  def once(delay: FiniteDuration)(f: => Unit): Unit = every(delay, Some(delay))(f)

  def every(delay: FiniteDuration,
            until: Option[FiniteDuration] = None)
           (f: => Unit): Unit = {
    val timeout = delay.toMillis / 1000.0
    val untilTimeout = until.map(_.toMillis / 1000.0)
    var elapsed = 0.0
    var totalElapsed = 0.0

    var observer: Observer[Double] = null
    observer = delta.attach { d =>
      elapsed += d
      totalElapsed += d
      if (elapsed >= timeout) {
        elapsed = 0.0
        f

        untilTimeout.foreach { total =>
          if (totalElapsed >= total) {
            delta.detach(observer)
          }
        }
      }
    }
  }

  def rateLimited(maxFrequency: FiniteDuration, frequency: FiniteDuration = 0.millis)(f: => Unit): LazyUpdate = {
    val lu = LazyUpdate(f, maxFrequency)
    every(frequency)(lu.update())
    lu
  }
}