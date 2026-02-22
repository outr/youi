package io.youi.component

import rapid.Task
import io.youi.component.support.{PositionSupport, SizeSupport}
import io.youi.component.types.{Display, PositionType, UserSelect}
import io.youi.easing.Easing
import io.youi.task._
import io.youi.{Chained, Initializable, ui}
import reactify._

import scala.concurrent.duration._

class Popup(showGlassPane: Boolean = true) extends Container with SizeSupport with PositionSupport with Initializable {
  private val chained = Chained(1)

  def preferredWidth: Double = 600.0
  def preferredHeight: Double = 600.0

  private val glassPane: Option[GlassPane] = if (showGlassPane) {
    val gp = new GlassPane
    gp.position.z := position.z - 1
    ui.children += gp
    Some(gp)
  } else {
    None
  }
  val easing: Var[Easing] = Var(Easing.exponentialOut)

  val speed: Var[FiniteDuration] = Var(150.millis)

  override protected def initialize(): Unit = {
    position.x @= 0.0
    position.y @= 0.0
    position.z @= 2000
    position.`type` @= PositionType.Absolute
    size.width := math.min(preferredWidth, ui.size.width)
    size.height := math.min(preferredHeight, ui.size.height)
    display @= Display.None

    // GlassPane set up
    glassPane.foreach { gp =>
      gp.event.click.on(hide().startUnit())
    }
  }

  def show(): Task[Unit] = chained {
    Task {
      init()

      Popup._active += this
    }.map { _ =>
      val animation = sequential(
        Task(position.center := ui.size.center),
        Task(position.y @= ui.size.height),
        Task(display @= Display.Block),
        Task(glassPane.foreach(_.backgroundAlpha @= 0.0)),
        Task(glassPane.foreach(_.display @= Display.Block)),
        parallel(
          positionProperty.to(positionDestination).in(speed).easing(easing),
          glassPane.map { gp =>
            gp.backgroundAlpha.to(0.5).in(speed).easing(easing)
          }.getOrElse(Task.unit)
        ),
        Task(positionProperty := positionDestination)
      )
      animation.start()
    }
  }

  def hide(): Task[Unit] = chained {
    Task {
      Popup._active -= this
      glassPane.foreach(_.element.style.pointerEvents = "none")
    }.flatMap { _ =>
      val task = sequential(
        parallel(
          position.y.to(ui.size.height).in(speed).easing(easing),
          glassPane.map { gp =>
            gp.backgroundAlpha.to(0.0).in(speed).easing(easing)
          }.getOrElse(Task.unit)
        ),
        Task(display @= Display.None),
        Task(glassPane.foreach(_.display @= Display.None)),
        Task(glassPane.foreach(_.element.style.pointerEvents = "auto"))
      )
      task.startDeferred().map(_ => ())
    }
  }

  protected def positionProperty: Stateful[Double] & Mutable[Double] = position.middle
  protected def positionDestination: Double = ui.size.middle
}

object Popup {
  private var _active = Set.empty[Popup]

  def active: Set[Popup] = _active
}
