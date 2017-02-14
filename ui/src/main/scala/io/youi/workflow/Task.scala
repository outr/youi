package io.youi.workflow

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

trait Task {
  final def start(): Future[Unit] = {
    starting()
    val future = run()
    future.onComplete {
      case Success(_) => finished()
      case Failure(t) => {
        failed(t)
        finished()
      }
    }
    future
  }

  protected def run(): Future[Unit]

  protected def starting(): Unit = {}
  protected def failed(t: Throwable): Unit = {}
  protected def finished(): Unit = {}

  def andThen(task: Task): ChainedTask = new ChainedTask(this, task)
}