package youi.component

import rapid.Task
import youi._
import youi.component.support.{MarginSupport, OverflowSupport}
import youi.component.types.{Display, Overflow, PositionType, UserSelect}
import youi.easing.Easing
import youi.task._
import reactify._

class Sidebar(container: Option[Component & MarginSupport],
              showGlassPane: Boolean = isMobileDevice,
              val width: Double = 260.0) extends Container() with OverflowSupport {
  private val chained = Chained(1)

  /** Effective width capped to 85% of viewport */
  private def effectiveSidebarWidth: Double = math.min(width, ui.size.width() * 0.85)

  private val glassPane: Option[GlassPane] = if (showGlassPane) {
    val gp = new GlassPane
    gp.backgroundAlpha := (math.max(0.0, effectiveSidebarWidth + position.x) / effectiveSidebarWidth) / 2.0
    gp.position.z @= 999
    ui.children += gp
    Some(gp)
  } else {
    None
  }

  val speed: Var[Double] = Var(260.0)
  val easing: Var[Easing] = Var(Easing.exponentialOut)
  val pinned: Var[Boolean] = Var(!isMobileDevice)
  val open: Var[Boolean] = Var(!isMobileDevice)
  val swipe: Var[Boolean] = Var(true)
  val swiping: Val[Boolean] = Var(false)
  val swipeAcceleration: Var[Boolean] = Var(false)

  val contents: Container = new Container {
    size.width := effectiveSidebarWidth
    size.height := Sidebar.this.size.height
  }

  position.x @= 0.0
  position.y @= 0.0
  position.z @= 1000
  position.`type` @= PositionType.Absolute
  size.width := effectiveSidebarWidth
  size.height := ui.size.height
  overflow.x @= Overflow.Hidden

  children += contents

  // Set up container adjustments
  container.foreach { c =>
    c.size.width := (if (pinned) ui.size.width - (effectiveSidebarWidth + position.x) else ui.size.width)
    c.margin.left := (if (pinned) effectiveSidebarWidth + position.x else 0.0)
  }

  // GlassPane set up
  glassPane.foreach { gp =>
    gp.event.click.on {
      if (open) {
        open @= false
      }
    }
  }

  // Monitor open state
  open.attach {
    case true => show()
    case false => hide()
  }
  if (open) {
    position.x @= 0.0
    glassPane.foreach(_.display @= Display.Block)
  } else {
    position.x @= -effectiveSidebarWidth
    glassPane.foreach(_.display @= Display.None)
  }

  // Swipe support
  private var start = 0.0
  ui.swipe.start.attach { evt =>
    if (swipe && Popup.active.isEmpty && evt.direction.plane == Plane.Horizontal) {
      swiping.asInstanceOf[Var[Boolean]] @= true
      start = position.x
      glassPane.foreach(_.display @= Display.Block)
      ui.userSelect @= UserSelect.None
    }
  }
  ui.swipe.move.attach { evt =>
    if (swiping) {
      position.x @= math.min(0.0, start + evt.distance)
    }
  }
  ui.swipe.end.attach { evt =>
    if (swiping) {
      swiping.asInstanceOf[Var[Boolean]] @= false
      ui.userSelect @= UserSelect.Auto
      val esw = effectiveSidebarWidth

      if (swipeAcceleration) {
        open @= (if (evt.acceleration == Double.NegativeInfinity || evt.acceleration == Double.PositiveInfinity || math.abs(evt.acceleration) < 1000.0) {
          size.width >= esw / 2.0
        } else if (evt.acceleration > 0.0) {
          true
        } else {
          false
        })
      } else {
        if (open) {
          if (position.x <= esw * -0.05) {
            open @= false
          }
        } else if (position.x >= esw * -0.95) {
          open @= true
        }
      }

      // Work-around for partial open
      if (open) {
        show()
      } else {
        hide()
      }
    }
  }

  private def show(): Task[Unit] = chained {
    sequential(
      Task(glassPane.foreach(_.display @= Display.Block)),
      position.x.to(0.0).by(speed).easing(easing)
    ).startDeferred().map(_ => ())
  }

  private def hide(): Task[Unit] = chained {
    sequential(
      position.x.to(-effectiveSidebarWidth).by(speed).easing(easing),
      Task(glassPane.foreach(_.display @= Display.None))
    ).startDeferred().map(_ => ())
  }
}
