package io.youi.app.screen

import io.youi.theme.StyleProp
import io.youi.ui
import reactify.{Val, Var}

import scala.concurrent.Future

trait Screen {
  private var registration = Map.empty[Var[_], ScreenRegistration[_]]

  def title: String = getClass.getSimpleName

  private[screen] val currentState = Var[ScreenState](ScreenState.New)

  val state: Val[ScreenState] = Val(currentState)

  ScreenManager().addScreen(this)

  protected def init(): Future[Unit] = Future.successful(())

  protected def load(): Future[Unit] = Future.successful(())

  protected def activate(): Future[Unit] = {
    ui.title @= title
    registration.values.foreach(_.activate())
    Future.successful(())
  }

  protected def deactivate(): Future[Unit] = {
    registration.values.foreach(_.deactivate())
    Future.successful(())
  }

  def register[Value](v: Var[Value], value: => Value): Unit = {
    val r = ScreenRegistration(v, () => value, v())
    val active = state() == ScreenState.Activated
    if (active) {
      r.activate()
    }
    registration += v -> r
  }

  def register[Value](v: StyleProp[Value], value: => Value): Unit = register[Option[Value]](v.option, Some(value))

  protected def dispose(): Future[Unit] = Future.successful(())
}

object Screen {
  private[screen] def init(screen: Screen): Future[Unit] = screen.init()
  private[screen] def load(screen: Screen): Future[Unit] = screen.load()
  private[screen] def activate(screen: Screen): Future[Unit] = screen.activate()
  private[screen] def deactivate(screen: Screen): Future[Unit] = screen.deactivate()
  private[screen] def dispose(screen: Screen): Future[Unit] = screen.dispose()
}