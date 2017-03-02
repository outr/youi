package io.youi.app.screen

import reactify.{Val, Var}

import scala.concurrent.Future

trait Screen {
  private[screen] val currentState = Var[ScreenState](ScreenState.New)

  val state: Val[ScreenState] = Val(currentState)

  ScreenManager().addScreen(this)

  protected def init(): Future[Unit] = Future.successful(())

  protected def load(): Future[Unit] = Future.successful(())

  protected def activate(): Future[Unit] = Future.successful(())

  protected def deactivate(): Future[Unit] = Future.successful(())

  protected def dispose(): Future[Unit] = Future.successful(())
}

object Screen {
  private[screen] def init(screen: Screen): Future[Unit] = screen.init()
  private[screen] def load(screen: Screen): Future[Unit] = screen.load()
  private[screen] def activate(screen: Screen): Future[Unit] = screen.activate()
  private[screen] def deactivate(screen: Screen): Future[Unit] = screen.deactivate()
  private[screen] def dispose(screen: Screen): Future[Unit] = screen.dispose()
}