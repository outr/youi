package io.youi.app.screen

import cats.effect.IO
import io.youi.component.Container
import io.youi.component.support.{MarginSupport, SizeSupport}
import io.youi.component.types.Display
import io.youi.ui

trait UIScreen extends Screen with PathActivation {
  protected lazy val container: Container with SizeSupport with MarginSupport = {
    val c = new Container with SizeSupport with MarginSupport
    c.id @= title
    c
  }

  override protected def init(): IO[Unit] = super.init().flatMap { _ =>
    container.size.width := ui.size.width
    container.size.height := ui.size.height

    ui.children += container
    createUI()
  }

  def createUI(): IO[Unit]

  override protected def activate(): IO[Unit] = super.activate().map(_ => container.display @= Display.Block)

  override protected def deactivate(): IO[Unit] = super.deactivate().map(_ => container.display @= Display.None)
}