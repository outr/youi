package io.youi.component

import io.youi.component.support.{PositionSupport, SizeSupport}
import io.youi.component.types.{Display, PositionType}
import io.youi.easing.Easing
import io.youi.{Color, ui}
import io.youi.task._
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.duration._
import scribe.Execution.global

class Popup(showGlassPane: Boolean = true) extends Container with SizeSupport with PositionSupport {
  private var future: Future[Unit] = Future.successful(())

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

  def init(): Unit = {
    position.x @= 0.0
    position.y @= 0.0
    position.z @= 2000
    position.`type` @= PositionType.Absolute
    size.width := math.min(preferredWidth, ui.size.width)
    size.height := math.min(preferredHeight, ui.size.height)
    display @= Display.None

    // GlassPane set up
    glassPane.foreach { gp =>
      gp.event.click.on(hide())
    }
  }

  def show(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        position.center := ui.size.center,
        position.y @= ui.size.height,
        display @= Display.Block,
        glassPane.foreach(_.backgroundAlpha @= 0.0),
        glassPane.foreach(_.display @= Display.Block),
        parallel(
          position.middle.to(ui.size.middle).in(speed).easing(easing),
          glassPane.map { gp =>
            gp.backgroundAlpha.to(0.5).in(speed).easing(easing)
          }.getOrElse(Task.None)
        ),
        position.middle := ui.size.middle
      ).start().future.map(_ => ())
    }
    future
  }

  def hide(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        parallel(
          position.y.to(ui.size.height).in(speed).easing(easing),
          glassPane.map { gp =>
            gp.backgroundAlpha.to(0.0).in(speed).easing(easing)
          }.getOrElse(Task.None)
        ),
        display @= Display.None,
        glassPane.foreach(_.display @= Display.None)
      ).start().future.map(_ => ())
    }
    future
  }
}