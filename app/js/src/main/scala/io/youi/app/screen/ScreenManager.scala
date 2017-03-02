package io.youi.app.screen

import reactify.{ChangeListener, Val, Var}
import io.youi.app.YouIApplication

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait ScreenManager {
  ScreenManager.instance = Some(this)

  private var managerFuture: Future[Unit] = Future.successful(())

  private val allScreens = Var[Set[Screen]](Set.empty)

  val active: Var[Screen] = Var(EmptyScreen)

  active.changes(new ChangeListener[Screen] {
    override def change(oldScreen: Screen, newScreen: Screen): Unit = {
      managerFuture = managerFuture.flatMap(_ => beforeScreenChange(oldScreen, newScreen))
      deactivate(oldScreen)
      activate(newScreen)
      managerFuture = managerFuture.flatMap(_ => afterScreenChange(oldScreen, newScreen))
    }
  })

  protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = {
    Future.successful(())
  }
  protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = {
    Future.successful(())
  }

  private[screen] def addScreen(screen: Screen): Unit = synchronized {
    allScreens := (allScreens() + screen)
  }

  def load(screen: Screen): Future[Unit] = managerFuture.map { _ =>
    var future: Future[Unit] = Future.successful(())

    val state = screen.state()

    def applyState(applying: ScreenState, applied: ScreenState, call: => Future[Unit]): Unit = if (state.index < applying.index || state == ScreenState.Disposed) {
      future = future.flatMap { _ =>
        screen.currentState := applying
        call.map { _ =>
          screen.currentState := applied
        }
      }
    }

    applyState(ScreenState.Initializing, ScreenState.Initialized, Screen.init(screen))
    applyState(ScreenState.Loading, ScreenState.Loaded, Screen.load(screen))

    future.failed.foreach(YouIApplication().error)
    managerFuture = future
    future
  }

  private def activate(screen: Screen): Future[Unit] = managerFuture.flatMap { _ =>
    var future: Future[Unit] = Future.successful(())

    val state = screen.state()

    def applyState(applying: ScreenState, applied: ScreenState, call: => Future[Unit], applyToStates: ScreenState*): Unit = if (applyToStates.contains(state)) {
      future = future.flatMap { _ =>
        screen.currentState := applying
        call.map { _ =>
          screen.currentState := applied
        }
      }
    }

    applyState(ScreenState.Initializing, ScreenState.Initialized, Screen.init(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed)
    applyState(ScreenState.Loading, ScreenState.Loaded, Screen.load(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized)
    applyState(ScreenState.Activating, ScreenState.Activated, Screen.activate(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized, ScreenState.Deactivating, ScreenState.Deactivated)

    future.failed.foreach(YouIApplication().error)
    managerFuture = future
    future
  }

  private def deactivate(screen: Screen): Future[Unit] = managerFuture.flatMap { _ =>
    if (screen.state() == ScreenState.Activated) {
      screen.currentState := ScreenState.Deactivating
      val future = Screen.deactivate(screen).map { _ =>
        screen.currentState := ScreenState.Deactivated
      }

      future.failed.foreach(YouIApplication().error)
      managerFuture = future
      future
    } else {
      Future.successful(())
    }
  }

  def dispose(screen: Screen): Future[Unit] = managerFuture.flatMap { _ =>
    if (screen.state() != ScreenState.Disposed) {
      screen.currentState := ScreenState.Disposing
      val future = Screen.dispose(screen).map { _ =>
        screen.currentState := ScreenState.Disposed
      }

      future.failed.foreach(YouIApplication().error)
      managerFuture = future
      future
    } else {
      Future.successful(())
    }
  }
}

object ScreenManager {
  private var instance: Option[ScreenManager] = None

  def apply(): ScreenManager = instance.getOrElse(throw new RuntimeException("No ScreenManager is initialized!"))
}

object EmptyScreen extends Screen