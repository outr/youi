package youi.maintenance

import scala.concurrent.duration.FiniteDuration

case class TaskStatus(lastRun: Option[Long] = None,
                      timesRun: Int = 0,
                      nextRun: Option[Long] = None,
                      nextSchedule: Option[FiniteDuration] = None) {
  def schedule(duration: FiniteDuration): TaskStatus = copy(
    nextRun = Some(System.currentTimeMillis() + duration.toMillis),
    nextSchedule = Some(duration)
  )
}