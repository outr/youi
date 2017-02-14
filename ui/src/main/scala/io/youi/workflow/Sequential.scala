package io.youi.workflow

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Sequential(tasks: List[Task]) extends Task {
  override def run(): Future[Unit] = {
    var f = Future.successful(())
    tasks.foreach { task =>
      f = f.flatMap { _ =>
        task.start()
      }
    }
    f
  }
}
