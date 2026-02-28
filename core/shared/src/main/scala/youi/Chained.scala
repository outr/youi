package youi

import rapid.Task

import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.duration._

case class Chained(concurrency: Int = 1, delay: FiniteDuration = 100.millis) {
  private val active = new AtomicBoolean(false)

  private def lock(): Task[Unit] = Task(active.compareAndSet(false, true)).flatMap {
    case true => Task.unit
    case false => Task.sleep(delay).flatMap(_ => lock())
  }

  private def unlock(): Task[Unit] = Task(active.set(false))

  def apply[Return](effect: Task[Return]): Task[Return] = lock().flatMap { _ =>
    effect.guarantee(unlock())
  }
}
