package io.youi.component

import io.youi.component.support.{PositionSupport, SizeSupport}
import io.youi.component.types.{Display, PositionType}
import io.youi.easing.Easing
import io.youi.{Color, ui}
import io.youi.task._

import scala.concurrent.Future
import scala.concurrent.duration._
import scribe.Execution.global

class Popup extends Container with SizeSupport with PositionSupport {
  position.x @= 0.0
  position.y @= 0.0
  position.z @= 100
  position.`type` @= PositionType.Absolute
  size.width := math.min(600.0, ui.size.width)
  size.height := math.min(800.0, ui.size.height)
  display @= Display.None
  backgroundColor @= Color.Cyan

  private var future: Future[Unit] = Future.successful(())

  def show(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        position.center := ui.size.center,
        position.y @= ui.size.height,
        display @= Display.Block,
        position.middle.to(ui.size.middle).in(300.millis).easing(Easing.exponentialOut)
      ).start().future.map(_ => ())
    }
    future
  }

  def hide(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        position.y.to(ui.size.height).in(300.millis).easing(Easing.exponentialOut),
        display @= Display.None
      ).start().future.map(_ => ())
    }
    future
  }
}