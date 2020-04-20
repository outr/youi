package io.youi.task

class Action(action: () => Unit) extends Task {
  override def update(delta: Double, reset: Boolean): Conclusion = {
    action()
    Conclusion.Finished
  }
}

object Action {
  def apply(f: => Unit): Action = new Action(() => f)
}