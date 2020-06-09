package io.youi.component

import io.youi._
import io.youi.component.support.{MarginSupport, OverflowSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Display, Overflow, PositionType, UserSelect}
import io.youi.easing.Easing
import io.youi.task._
import reactify.{Val, Var}
import scribe.Execution.global

import scala.concurrent.Future

class Sidebar(container: Option[Component with SizeSupport with MarginSupport],
              showGlassPane: Boolean = isMobileDevice,
              val width: Double = 260.0) extends Container() with PositionSupport with SizeSupport with OverflowSupport {
  private var future: Future[Unit] = Future.successful(())
  private val glassPane: Option[GlassPane] = if (showGlassPane) {
    val gp = new GlassPane
    gp.backgroundAlpha := (math.max(0.0, width + position.x) / width) / 2.0
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

  val contents: Container with SizeSupport = new Container with SizeSupport {
    size.width @= width
    size.height := Sidebar.this.size.height
  }

  position.x @= 0.0
  position.y @= 0.0
  position.z @= 1000
  position.`type` @= PositionType.Absolute
  size.width @= width
  size.height := ui.size.height
  overflow.x @= Overflow.Hidden

  children += contents

  // Set up container adjustments
  container.foreach { c =>
    c.size.width := (if (pinned) ui.size.width - (width + position.x) else ui.size.width)
    c.margin.left := (if (pinned) width + position.x else 0.0)
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
    position.x @= -width
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

      if (swipeAcceleration) {
        open @= (if (evt.acceleration == Double.NegativeInfinity || evt.acceleration == Double.PositiveInfinity || math.abs(evt.acceleration) < 1000.0) {
          size.width >= width / 2.0
        } else if (evt.acceleration > 0.0) {
          true
        } else {
          false
        })
      } else {
        if (open) {
          if (position.x <= width * -0.05) {
            open @= false
          }
//        } else if (size.width >= width * 0.05) {
        } else if (position.x >= width * -0.95) {
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

  private def show(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        glassPane.foreach(_.display @= Display.Block),
        position.x.to(0.0).by(speed).easing(easing)
      ).start().future.map(_ => ())
    }
    future
  }

  private def hide(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        position.x.to(-width).by(speed).easing(easing),
        glassPane.foreach(_.display @= Display.None)
      ).start().future.map(_ => ())
    }
    future
  }
}