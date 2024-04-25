package io.youi.app.screen

import cats.effect.IO
import io.youi.task.TaskSupport
import io.youi.ui
import reactify.{Val, Var}

trait Screen extends TaskSupport {
  private var registration = Map.empty[Var[_], ScreenRegistration[_]]

  def title: String = getClass.getSimpleName.replace("$", "")

  private[screen] val currentState = Var[ScreenState](ScreenState.New)

  val state: Val[ScreenState] = Val(currentState)

  ScreenManager().addScreen(this)

  protected def init(): IO[Unit] = IO.unit

  protected def load(): IO[Unit] = IO.unit

  protected def activate(): IO[Unit] = {
    ui.title @= title
    registration.values.foreach(_.activate())
    IO.unit
  }

  protected def deactivate(): IO[Unit] = {
    registration.values.foreach(_.deactivate())
    IO.unit
  }

  def register[Value](v: Var[Value], value: => Value): Unit = {
    val r = ScreenRegistration(v, () => value, v())
    val active = state() == ScreenState.Activated
    if (active) {
      r.activate()
    }
    registration += v -> r
  }

  protected def dispose(): IO[Unit] = IO.unit
}

object Screen {
  private[screen] def init(screen: Screen): IO[Unit] = screen.init()
  private[screen] def load(screen: Screen): IO[Unit] = screen.load()
  private[screen] def activate(screen: Screen): IO[Unit] = screen.activate()
  private[screen] def deactivate(screen: Screen): IO[Unit] = screen.deactivate()
  private[screen] def dispose(screen: Screen): IO[Unit] = screen.dispose()
}