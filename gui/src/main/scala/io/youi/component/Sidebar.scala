package io.youi.component

import io.youi.component.support.{MarginSupport, OverflowSupport, PositionSupport, SizeSupport}
import io.youi.component.types.{Display, Overflow, PositionType, UserSelect}
import io.youi.easing.Easing
import io.youi.task._
import io.youi._
import reactify.{Val, Var}

import scala.concurrent.Future
import scribe.Execution.global

class Sidebar(container: Option[Component with SizeSupport with MarginSupport],
              showGlassPane: Boolean = isMobileDevice,
              width: Double = 260.0) extends Container() with PositionSupport with SizeSupport with OverflowSupport {
  private var future: Future[Unit] = Future.successful(())
  private val glassPane: Option[GlassPane] = if (showGlassPane) {
    val gp = new GlassPane
    gp.backgroundAlpha := (math.max(0.0, size.width) / width) / 2.0
    ui.children += gp
    Some(gp)
  } else {
    None
  }

  val speed: Var[Double] = Var(260.0)
  val easing: Var[Easing] = Var(Easing.exponentialOut)
  val pinned: Var[Boolean] = Var(!isMobileDevice)
  val open: Var[Boolean] = Var(true)
  val swipe: Var[Boolean] = Var(true)
  val swiping: Val[Boolean] = Var(false)

  val contents: Component with SizeSupport = new Component(dom.create.div) with SizeSupport {
    size.width @= width
    size.height @= ui.size.height
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
    c.size.width := (if (pinned) ui.size.width - size.width else ui.size.width)
    c.margin.left := (if (pinned) size.width else 0.0)
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
  open.attachAndFire {
    case true => show()
    case false => hide()
  }

  // Swipe support
  private var start = 0.0
  ui.swipe.start.on {
    if (swipe) {
      swiping.asInstanceOf[Var[Boolean]] @= true
      start = size.width
      glassPane.foreach(_.display @= Display.Block)
      ui.userSelect @= UserSelect.None
    }
  }
  ui.swipe.move.attach { evt =>
    if (swiping) {
      size.width @= math.min(start + evt.distance, width)
    }
  }
  ui.swipe.end.on {
    if (swiping) {
      swiping.asInstanceOf[Var[Boolean]] @= false
      ui.userSelect @= UserSelect.Auto
      open @= size.width >= width / 2.0
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
        size.width.to(width).by(speed).easing(easing)
      ).start().future.map(_ => ())
    }
    future
  }

  private def hide(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        size.width.to(0.0).by(speed).easing(easing),
        glassPane.foreach(_.display @= Display.None)
      ).start().future.map(_ => ())
    }
    future
  }
}