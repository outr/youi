package io.youi.app.screen

import rapid.Task
import io.youi.component.Container
import io.youi.component.support.{MarginSupport, SizeSupport}
import io.youi.component.types.Display
import io.youi.ui
import reactify._

trait UIScreen extends Screen with PathActivation {
  protected lazy val container: Container & SizeSupport & MarginSupport = {
    val c = new Container with SizeSupport with MarginSupport
    c.id @= title
    c
  }

  override protected def init(): Task[Unit] = super.init().flatMap { _ =>
    scribe.info(s"UIScreen initting for ${getClass.getSimpleName}")
    container.size.width := ui.size.width
    container.size.height := ui.size.height

    ui.children += container
    scribe.info("createUI...")
    createUI()
  }

  def createUI(): Task[Unit]

  override protected def activate(): Task[Unit] = super.activate().map(_ => container.display @= Display.Block)

  override protected def deactivate(): Task[Unit] = super.deactivate().map(_ => container.display @= Display.None)
}