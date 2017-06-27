package io.youi

import reactify._

import scala.concurrent.duration.FiniteDuration
import org.scalajs.dom._

trait Updates {
  lazy val delta: Channel[Double] = Channel[Double]

  def nextFrame(f: => Unit): Unit = delta.once(_ => f)

  def update(delta: Double): Unit = this.delta := delta

  def once(delay: FiniteDuration, allowBackgrounding: Boolean = true)
          (f: => Unit): Unit = every(delay, Some(delay), allowBackgrounding)(f)

  def every(delay: FiniteDuration,
            until: Option[FiniteDuration] = None,
            allowBackgrounding: Boolean = true)
           (f: => Unit): Unit = {
    val timeout = delay.toMillis / 1000.0
    val untilTimeout = until.map(_.toMillis / 1000.0)
    var elapsed = 0.0
    var totalElapsed = 0.0

    if (allowBackgrounding) {
      var listener: Listener[Double] = null
      listener = delta.attach { d =>
        elapsed += d
        totalElapsed += d
        if (elapsed >= timeout) {
          elapsed = 0.0
          f

          untilTimeout.foreach { total =>
            if (totalElapsed >= total) {
              delta.detach(listener)
            }
          }
        }
      }
    } else {
      var function: () => Unit = null
      function = () => {
        f
        elapsed += (delay.toMillis / 1000.0)
        if (!until.exists(elapsed >= _.toMillis)) {
          window.setTimeout(function, delay.toMillis)
        }
      }
      window.setTimeout(function, delay.toMillis)
    }
  }
}