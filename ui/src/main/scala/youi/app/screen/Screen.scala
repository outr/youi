package youi.app.screen

import rapid.Task
import youi.task.TaskSupport
import youi.ui
import reactify.{Val, Var}

trait Screen extends TaskSupport {
  private var registration = Map.empty[Var[?], ScreenRegistration[?]]

  def title: String = getClass.getSimpleName.replace("$", "")

  private[screen] val currentState = Var[ScreenState](ScreenState.New)

  val state: Val[ScreenState] = currentState

  ScreenManager().addScreen(this)

  protected def init(): Task[Unit] = Task.unit

  protected def load(): Task[Unit] = Task.unit

  protected def activate(): Task[Unit] = {
    ui.title @= title
    registration.values.foreach(_.activate())
    Task.unit
  }

  protected def deactivate(): Task[Unit] = {
    registration.values.foreach(_.deactivate())
    Task.unit
  }

  def register[Value](v: Var[Value], value: => Value): Unit = {
    val r = ScreenRegistration(v, () => value, v())
    val active = state() == ScreenState.Activated
    if (active) {
      r.activate()
    }
    registration += v -> r
  }

  protected def dispose(): Task[Unit] = Task.unit
}

object Screen {
  private[screen] def init(screen: Screen): Task[Unit] = screen.init()
  private[screen] def load(screen: Screen): Task[Unit] = screen.load()
  private[screen] def activate(screen: Screen): Task[Unit] = screen.activate()
  private[screen] def deactivate(screen: Screen): Task[Unit] = screen.deactivate()
  private[screen] def dispose(screen: Screen): Task[Unit] = screen.dispose()
}