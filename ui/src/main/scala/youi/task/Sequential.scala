package youi.task

class Sequential(tasks: List[Task]) extends Task {
  private var resetNext: Boolean = false
  private var running: List[Task] = Nil

  override def update(delta: Double, reset: Boolean): Conclusion = {
    if (reset) {
      running = tasks
    }
    val resetNow = reset || resetNext
    resetNext = false
    running.headOption match {
      case Some(task) => task.update(delta, resetNow) match {
        case Conclusion.Continue => Conclusion.Continue
        case Conclusion.Finished => {
          running = running.tail
          if (running.isEmpty) {
            Conclusion.Finished
          } else {
            resetNext = true
            Conclusion.Continue
          }
        }
      }
      case None => Conclusion.Finished
    }
  }

  override def andThen(that: Task) = new Sequential(tasks ::: List(that))
}