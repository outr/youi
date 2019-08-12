package io.youi

import reactify._
import reactify.reaction.Reaction

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

    var reaction: Reaction[Double] = null
    reaction = delta.attach { d =>
      elapsed += d
      if (elapsed >= timeout) {
        elapsed = 0.0
        totalElapsed += timeout
        f

        untilTimeout.foreach { total =>
          if (totalElapsed >= total) {
            delta.reactions -= reaction
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