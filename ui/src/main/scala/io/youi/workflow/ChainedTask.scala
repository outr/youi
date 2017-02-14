package io.youi.workflow

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ChainedTask(first: Task, second: Task) extends Task {
  override protected def run(): Future[Unit] = first.start().map { _ =>
    second.start()
  }
}