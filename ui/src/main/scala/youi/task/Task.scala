package youi.task

import rapid.task.Completable
import youi.ui

trait Task {
  def update(delta: Double, reset: Boolean): Conclusion

  def stepSize: Double = 0.0

  def andThen(that: Task): Task = new Sequential(List(this, that))

  def start(taskSupport: TaskSupport = ui, deferred: Option[Completable[Double]] = None): TaskInstance =
    taskSupport.start(this, deferred)

  def startDeferred(taskSupport: TaskSupport = ui): rapid.Task[Double] = {
    val c = rapid.Task.completable[Double]
    start(taskSupport, Some(c))
    c
  }
}

object Task {
  lazy val None: Task = new Task {
    override def update(delta: Double, reset: Boolean): Conclusion = Conclusion.Finished
  }
}
