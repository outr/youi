package youi.task

import scala.concurrent.Future

class FutureTask[R](futureFunction: () => Future[R]) extends Task {
  private var future: Future[R] = scala.compiletime.uninitialized

  override def update(delta: Double, reset: Boolean): Conclusion = {
    if (reset) {
      future = futureFunction()
    }
    if (future.isCompleted) {
      Conclusion.Finished
    } else {
      Conclusion.Continue
    }
  }
}

object FutureTask {
  def apply[R](f: => Future[R]): FutureTask[R] = new FutureTask[R](() => f)
}