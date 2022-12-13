package io.youi.app.screen

import cats.effect.unsafe.implicits.global
import cats.effect.{Deferred, FiberIO, IO}
import io.youi.task.TaskSupport
import io.youi.{AnimationFrame, Chained, ErrorSupport}
import org.scalajs.dom
import org.scalajs.dom.DocumentReadyState
import reactify._

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

  private def screenChange(oldScreen: Screen, newScreen: Screen): Unit = chained(IO {
    scribe.debug(s"Screen change from $oldScreen to $newScreen...")
    val waitForLoaded: IO[Unit] = if (!loaded()) {
      // Wait for the page to fully load before finishing screen change
      Deferred[IO, Unit].map { d =>
        loaded.once { _ =>
          d.complete(())
        }
        d.get
      }
    } else {
      IO.unit
    }
    for {
      _ <- waitForLoaded
      _ <- beforeScreenChange(oldScreen, newScreen)
      _ <- deactivate(oldScreen)
      _ <- activate(newScreen)
      _ <- afterScreenChange(oldScreen, newScreen)
    } yield {
      ()
    }
  }).unsafeRunAndForget()

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

  private def activate(screen: Screen): IO[Unit] = chained {
    val state = screen.state()

    def set(applying: ScreenState,
            applied: ScreenState,
            call: IO[Unit], applyToStates: ScreenState*): IO[Unit] = if (applyToStates.contains(state)) {
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
      _ <- set(ScreenState.Initializing, ScreenState.Initialized, Screen.init(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed)
      _ <- set(ScreenState.Loading, ScreenState.Loaded, Screen.load(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized)
      _ <- set(ScreenState.Activating, ScreenState.Activated, Screen.activate(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized, ScreenState.Deactivating, ScreenState.Deactivated)
    } yield {
      ()
    }
  }

  private def deactivate(screen: Screen): IO[Unit] = chained {
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