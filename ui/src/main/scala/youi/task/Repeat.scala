package youi.task

class Repeat(task: Task, times: Int = 1) extends Task {
  private var resetOnNext = false
  private var completed = 0

  override def update(delta: Double, reset: Boolean): Conclusion = {
    if (reset) {
      completed = 0
    }
    val resetNow = reset || resetOnNext
    resetOnNext = false
    task.update(delta, resetNow) match {
      case Conclusion.Continue => Conclusion.Continue
      case Conclusion.Finished => if (completed >= times) {
        Conclusion.Finished
      } else {
        completed += 1
        resetOnNext = true
        Conclusion.Continue
      }
    }
  }
}
