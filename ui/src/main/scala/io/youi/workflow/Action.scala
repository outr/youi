package io.youi.workflow
import scala.concurrent.Future

class Action(action: () => Future[Unit]) extends Task {
  override def run(): Future[Unit] = action()
}

object Action {
  def apply(f: => Future[Unit]): Action = new Action(() => f)
}