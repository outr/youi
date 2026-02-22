package io.youi.app.screen

import rapid.Task
import rapid.task.Completable
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

  // Visual error display — show a red banner at the top of the page
  ErrorSupport.error.attach { t =>
    Option(dom.document.body).foreach { body =>
      val errorDiv = dom.document.createElement("div")
      errorDiv.setAttribute("style",
        "position:fixed;top:0;left:0;right:0;background:#cc0000;color:white;padding:12px 16px;z-index:999999;" +
          "font-family:monospace;font-size:13px;white-space:pre-wrap;max-height:50vh;overflow:auto;cursor:pointer;")
      errorDiv.textContent = s"${t.getClass.getName}: ${t.getMessage}"
      errorDiv.addEventListener("click", (_: dom.Event) => errorDiv.remove())
      body.appendChild(errorDiv)
    }
  }

  scribe.info("Initializing application...")
  init().map { _ =>
    if (waitForWindowLoad && dom.document.readyState != DocumentReadyState.complete) {
      dom.window.addEventListener("load", (_: dom.Event) => {
        load().map { _ =>
          loaded.asInstanceOf[Var[Boolean]] @= true
        }.handleError { t =>
          Task(ErrorSupport.error @= t)
        }.startUnit()
      })
    } else {
      load().map { _ =>
        loaded.asInstanceOf[Var[Boolean]] @= true
      }.handleError { t =>
        Task(ErrorSupport.error @= t)
      }.startUnit()
    }
  }.handleError { t =>
    Task(ErrorSupport.error @= t)
  }.startUnit()

  active.changes {
    case (oldScreen, newScreen) => screenChange(oldScreen, newScreen)
  }

  protected def init(): Task[Unit] = {
    AnimationFrame.delta.attach { d =>
      update(d)
    }
    Task.unit
  }

  protected def load(): Task[Unit] = Task.unit

  override def update(delta: Double): Unit = {
    super.update(delta)

    active.update(delta)
  }

  private def screenChange(oldScreen: Screen, newScreen: Screen): Unit = chained {
    scribe.info(s"Screen change from $oldScreen to $newScreen...")
    val waitForLoaded: Task[Unit] = if (!loaded()) {
      scribe.info("Waiting for the page to fully load...")
      val c: Completable[Unit] = Task.completable[Unit]
      loaded.once { _ =>
        c.success(())
      }
      c
    } else {
      Task.unit
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
  }.handleError { t =>
    Task(ErrorSupport.error @= t)
  }.startUnit()

  protected def beforeScreenChange(oldScreen: Screen, newScreen: Screen): Task[Unit] = {
    Task.unit
  }
  protected def afterScreenChange(oldScreen: Screen, newScreen: Screen): Task[Unit] = {
    Task.unit
  }

  private[screen] def addScreen(screen: Screen): Unit = synchronized {
    allScreens @= (allScreens() ::: List(screen))
  }

  def load(screen: Screen): Task[Unit] = chained {
    val state = screen.state()

    def set(applying: ScreenState,
                   applied: ScreenState,
                   call: Task[Unit]): Task[Unit] = if (state.index < applying.index || state == ScreenState.Disposed) {
      for {
        _ <- Task(screen.currentState @= applying)
        _ <- call
        _ <- Task(screen.currentState @= applied)
      } yield {
        ()
      }
    } else {
      Task.unit
    }

    for {
      _ <- set(ScreenState.Initializing, ScreenState.Initialized, Screen.init(screen))
      _ <- set(ScreenState.Loading, ScreenState.Loaded, Screen.load(screen))
    } yield {
      ()
    }
  }

  private def activate(screen: Screen): Task[Unit] = {
    val state = screen.state()

    def set(applying: ScreenState,
            applied: ScreenState,
            call: Task[Unit],
            applyToStates: ScreenState*): Task[Unit] = if (applyToStates.contains(state)) {
      for {
        _ <- Task(scribe.info(s"Applying: $applying, Current state: $state"))
        _ <- Task(screen.currentState @= applying)
        _ <- call
        _ <- Task(screen.currentState @= applied)
      } yield {
        ()
      }
    } else {
      Task.unit
    }

    for {
      _ <- set(ScreenState.Initializing, ScreenState.Initialized, Screen.init(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed)
      _ <- set(ScreenState.Loading, ScreenState.Loaded, Screen.load(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized)
      _ <- set(ScreenState.Activating, ScreenState.Activated, Screen.activate(screen), ScreenState.New, ScreenState.Disposing, ScreenState.Disposed, ScreenState.Initializing, ScreenState.Initialized, ScreenState.Deactivating, ScreenState.Deactivated)
    } yield {
      ()
    }
  }

  private def deactivate(screen: Screen): Task[Unit] = {
    if (screen.state() == ScreenState.Activated) {
      screen.currentState @= ScreenState.Deactivating
      Screen.deactivate(screen).map { _ =>
        screen.currentState @= ScreenState.Deactivated
      }
    } else {
      Task.unit
    }
  }

  def dispose(screen: Screen): Task[Unit] = chained {
    if (screen.state() != ScreenState.Disposed) {
      screen.currentState @= ScreenState.Disposing
      Screen.dispose(screen).map { _ =>
        screen.currentState @= ScreenState.Disposed
      }
    } else {
      Task.unit
    }
  }
}

object ScreenManager {
  private var instance: Option[ScreenManager] = None

  def apply(): ScreenManager = instance.getOrElse(throw new RuntimeException("No ScreenManager is initialized!"))
}
