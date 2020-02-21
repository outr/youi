package io.youi.task

trait Task {
  def update(delta: Double, reset: Boolean): Conclusion

  def stepSize: Double = 0.0

  def andThen(that: Task): Task = new Sequential(List(this, that))

  def start(taskSupport: TaskSupport): TaskInstance = taskSupport.start(this)
}