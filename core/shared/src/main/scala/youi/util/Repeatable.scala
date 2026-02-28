package youi.util

import rapid.Task

import scala.concurrent.duration.FiniteDuration

/**
  * @see Time.repeat for convenient usage
  */
case class Repeatable(delay: FiniteDuration,
                      initialDelay: Option[FiniteDuration],
                      stopOnError: Boolean,
                      task: () => Task[Unit],
                      errorHandler: Throwable => Unit) {
  private var running = false
  private var keepAlive = true

  def start(): Unit = synchronized {
    if (!running) {
      running = true
      keepAlive = true
      run(initialDelay.getOrElse(delay))
    }
  }

  private def run(delay: FiniteDuration): Unit = synchronized {
    if (keepAlive && Repeatable.keepAlive) {
      Task.sleep(delay).flatMap { _ =>
        if (keepAlive && Repeatable.keepAlive) {
          task()
        } else {
          Task.unit
        }
      }.attempt.map {
        case scala.util.Failure(throwable) =>
          errorHandler(throwable)
          if (!stopOnError) reSchedule()
        case scala.util.Success(_) => reSchedule()
      }.startUnit()
    }
  }

  private def reSchedule(): Unit = run(delay)

  def stop(): Unit = synchronized {
    keepAlive = false
  }
}

// TODO: Merge Maintenane functionality and TaskStatus for much more powerful implementation
object Repeatable {
  private var keepAlive: Boolean = true

  def dispose(): Unit = keepAlive = false
}
