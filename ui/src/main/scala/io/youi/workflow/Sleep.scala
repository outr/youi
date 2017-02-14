package io.youi.workflow

import scala.concurrent.duration.FiniteDuration

class Sleep(val time: FiniteDuration) extends DurationTemporal {
  override def act(delta: Double, elapsed: Double, progress: Double): Unit = {}
}
