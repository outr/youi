package youi.task

import scala.annotation.tailrec

class Parallel(tasks: List[Task]) extends Task {
  private var running: List[Task] = Nil

  override def update(delta: Double, reset: Boolean): Conclusion = {
    if (reset) {
      running = tasks
    }
    recursive(running, delta, reset)
    if (running.isEmpty) {
      Conclusion.Finished
    } else {
      Conclusion.Continue
    }
  }

  @tailrec
  private def recursive(tasks: List[Task], delta: Double, reset: Boolean): Unit = {
    if (tasks.isEmpty) {
      // Finished
    } else {
      val task = tasks.head
      task.update(delta, reset) match {
        case Conclusion.Continue => // keep going
        case Conclusion.Finished => running = running.filterNot(_ == task)
      }
      recursive(tasks.tail, delta, reset)
    }
  }
}