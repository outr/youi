package io.youi.workflow

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Repeat(task: Task, times: Int = 1) extends Task {
  override protected def run(): Future[Unit] = repeating(0)

  private def repeating(repeated: Int): Future[Unit] = {
    val future = task.start()
    if (repeated >= times) {
      future
    } else {
      future.map(_ => repeating(repeated + 1))
    }
  }
}
