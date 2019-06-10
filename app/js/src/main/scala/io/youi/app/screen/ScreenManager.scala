package io.youi.app.screen

import com.outr.hookup.Hookup
import io.youi.ErrorSupport
import reactify._
import io.youi.app.{ClientApplication, YouIApplication}

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import org.scalajs.dom._

import scala.util.{Failure, Success}

trait ScreenManager {
  ScreenManager.instance = Some(this)

  // TODO: remove and switch to using SingleThreadedFuture
  private var managerFuture: Future[Unit] = Future.successful(())

  private val allScreens = Var[List[Screen]](Nil)

  val screens: Val[List[Screen]] = allScreens
  val active: Var[Screen] = Var(EmptyScreen)
  val loaded: Val[Boolean] = Var(false)

  protected def waitForWindowLoad: Boolean = true

  scribe.info("Initializing application...")
  init().onComplete {
    case Success(_) => {
      if (waitForWindowLoad && document.readyState != "complete") {
        scribe.info("Application initialized. Waiting for window load to complete...")
        window.addEventListener("load", (_: Event) => {
          scribe.info("Window loading complete. Loading application...")
          load().foreach { _ =>
            scribe.info("Application loaded.")
            loaded.asInstanceOf[Var[Boolean]] := true
          }
        })
      } else {
        scribe.info("Application initialized. Loading application...")
        load().foreach { _ =>
          scribe.info("Application loaded.")
          loaded.asInstanceOf[Var[Boolean]] := true
        }
      }
    }
    case Failure(t) => ErrorSupport.error := new RuntimeException("Error during application initialization!", t)
  }

  active.changes {
    case (oldScreen, newScreen) => screenChange(oldScreen, newScreen)
  }

  protected def init(): Future[Unit] = {
    // Redirect Hookup errors to error support
    Hookup.error.attach(t => ErrorSupport.error := t)

    Future.successful(())
  }

  protected def load(): Future[Unit] = Future.successful(())

  private def screenChange(oldScreen: Screen, newScreen: Screen): Unit = synchronized {
    if (managerFuture.isCompleted) {
      managerFuture = Future.successful(())
    }
    scribe.debug(s"Screen change from $oldScreen to $newScreen...")
    if (!loaded()) {
      // Wait for the page to fully load before finishing screen change
      val promise = Promise[Unit]
      loaded.once { _ =>
        promise.success(())
      }
      managerFuture = managerFuture.flatMap(_ => promise.future)
    }
    managerFuture = managerFuture.flatMap(_ => beforeScreenChange(oldScreen, newScreen))
    managerFuture = managerFuture.flatMap(_ => deactivate(oldScreen))
    managerFuture = managerFuture.flatMap(_ => activate(newScreen))
    managerFuture = managerFuture.flatMap(_ => afterScreenChange(oldScreen, newScreen))
    managerFuture.failed.foreach { throwable =>
      YouIApplication().error(throwable)
    }
  }

  protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = {
    Future.successful(())
  }
  protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): Future[Unit] = {
    Future.successful(())
  }

  private[screen] def addScreen(screen: Screen): Unit = synchronized {
    allScreens := (allScreens() ::: List(screen))
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

  private def activate(screen: Screen): Future[Unit] = {
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

  private def deactivate(screen: Screen): Future[Unit] = {
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

  def dispose(screen: Screen): Future[Unit] = {
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