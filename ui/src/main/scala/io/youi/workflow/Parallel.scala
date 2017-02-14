package io.youi.workflow

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Parallel(tasks: List[Task]) extends Task {
  override def run(): Future[Unit] = Future.sequence(tasks.map(_.start())).map(_ => ())
}