package io.youi.app.screen

import cats.effect.unsafe.implicits.global
import cats.effect.{Deferred, FiberIO, IO}
import io.youi.task.TaskSupport
import io.youi.{AnimationFrame, Chained, ErrorSupport}
import org.scalajs.dom
import org.scalajs.dom.DocumentReadyState
import reactify._

import scala.concurrent.duration.DurationInt

trait ScreenManager extends TaskSupport {
  ScreenManager.instance = Some(this)

  protected lazy val chained: Chained = Chained()

  private val allScreens = Var[List[Screen]](Nil)

  val screens: Val[List[Screen]] = allScreens
  val active: Var[Screen] = Var(EmptyScreen)
  val loaded: Val[Boolean] = Var(false)

  protected def waitForWindowLoad: Boolean = true

  scribe.info("Initializing application...")
  init().map { _ =>
    scribe.info(s"Initialized. Waiting for page loading to complete...")
    if (waitForWindowLoad && dom.document.readyState != DocumentReadyState.complete) {
      scribe.info("Application initialized. Waiting for window load to complete...")
      dom.window.addEventListener("load", (_: dom.Event) => {
        scribe.info("Window loading complete. Loading application...")
        load().map { _ =>
          scribe.info("Application loaded.")
          loaded.asInstanceOf[Var[Boolean]] @= true
        }.unsafeRunAndForget()
      })
    } else {
      scribe.info("Application initialized. Loading application...")
      load().map { _ =>
        scribe.info("Application loaded.")
        loaded.asInstanceOf[Var[Boolean]] @= true
      }.unsafeRunAndForget()
    }
  }.unsafeRunAndForget()

  active.changes {
    case (oldScreen, newScreen) => screenChange(oldScreen, newScreen)
  }

  protected def init(): IO[Unit] = {
    AnimationFrame.delta.attach { d =>
      update(d)
    }
    IO.unit
  }

  protected def load(): IO[Unit] = IO.unit

  override def update(delta: Double): Unit = {
    super.update(delta)

    active.update(delta)
  }

  private def screenChange(oldScreen: Screen, newScreen: Screen): Unit = chained {
    scribe.info(s"Screen change from $oldScreen to $newScreen...")
    val waitForLoaded: IO[Unit] = if (!loaded()) {
      scribe.info("Waiting for the page to fully load...")
      // Wait for the page to fully load before finishing screen change
      Deferred[IO, Unit].flatMap { d =>
        scribe.info("Deferred!")
        loaded.once { _ =>
          scribe.info("Page fully loaded!")
          d.complete(()).unsafeRunAndForget()
        }
        scribe.info("Waiting for page load...")
        d.get.map { _ =>
          scribe.info("Page loaded!")
        }
      }
    } else {
      IO.unit
    }
    for {
      _ <- waitForLoaded
      _ = scribe.info("Loaded!")
      _ <- beforeScreenChange(oldScreen, newScreen)
      _ = scribe.info("Before screen change!")
      _ <- deactivate(oldScreen)
      _ = scribe.info("Deactivated!")
      _ <- activate(newScreen)
      _ = scribe.info("Activated!")
      _ <- afterScreenChange(oldScreen, newScreen)
      _ = scribe.info("After screen change!")
    } yield {
      ()
    }
  }.unsafeRunAndForget()

  protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): IO[Unit] = {
    IO.unit
  }
  protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): IO[Unit] = {
    IO.unit
  }

  private[screen] def addScreen(screen: Screen): Unit = synchronized {
    allScreens @= (allScreens() ::: List(screen))
  }

  def load(screen: Screen): IO[Unit] = chained {
    val state = screen.state()

    def set(applying: ScreenState,
                   applied: ScreenState,
                   call: IO[Unit]): IO[Unit] = if (state.index < applying.index || state == ScreenState.Disposed) {
      for {
        _ <- IO(screen.currentState @= applying)
        _ <- call
        _ <- IO(screen.currentState @= applied)
      } yield {
        ()
      }
    } else {
      IO.unit
    }

    for {
      _ <- set(ScreenState.Initializing, ScreenState.Initialized, Screen.init(screen))
      _ <- set(ScreenState.Loading, ScreenState.Loaded, Screen.load(screen))
    } yield {
      ()
    }
  }

  private def activate(screen: Screen): IO[Unit] = {
    val state = screen.state()

    def set(applying: ScreenState,
            applied: ScreenState,
            call: IO[Unit],
            applyToStates: ScreenState*): IO[Unit] = if (applyToStates.contains(state)) {
      for {
        _ <- IO(scribe.info(s"Applying: $applying, Current state: $state"))
        _ <- IO(screen.currentState @= applying)
        _ = scribe.info(s"Applied: $applied")
        _ <- call
        _ = scribe.info("CALLED!")
        _ <- IO(screen.currentState @= applied)
      } yield {
        ()
      }
    } else {
      IO.unit
    }

    for {
      _ <- set(ScreenState.Initializing, ScreenState.Initialized, Screen.init(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed)
      _ <- set(ScreenState.Loading, ScreenState.Loaded, Screen.load(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized)
      _ <- set(ScreenState.Activating, ScreenState.Activated, Screen.activate(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized, ScreenState.Deactivating, ScreenState.Deactivated)
    } yield {
      ()
    }
  }

  private def deactivate(screen: Screen): IO[Unit] = {
    if (screen.state() == ScreenState.Activated) {
      screen.currentState @= ScreenState.Deactivating
      Screen.deactivate(screen).map { _ =>
        screen.currentState @= ScreenState.Deactivated
      }
    } else {
      IO.unit
    }
  }

  def dispose(screen: Screen): IO[Unit] = chained {
    if (screen.state() != ScreenState.Disposed) {
      screen.currentState @= ScreenState.Disposing
      Screen.dispose(screen).map { _ =>
        screen.currentState @= ScreenState.Disposed
      }
    } else {
      IO.unit
    }
  }
}

object ScreenManager {
  private var instance: Option[ScreenManager] = None

  def apply(): ScreenManager = instance.getOrElse(throw new RuntimeException("No ScreenManager is initialized!"))
}