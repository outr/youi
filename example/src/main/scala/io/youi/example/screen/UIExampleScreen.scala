package io.youi.example.screen

import rapid.Task
import io.youi._
import io.youi.app.screen.UIScreen
import io.youi.component.Container
import io.youi.component.support.{MarginSupport, OverflowSupport}
import io.youi.component.types.{Overflow, PositionType}
import io.youi.example.ExampleApp
import io.youi.example.ui.component.Header
import io.youi.ui
import spice.net._

trait UIExampleScreen extends UIScreen {
  override protected lazy val container: Container & MarginSupport & OverflowSupport = {
    val c = new Container with MarginSupport with OverflowSupport
    c.id @= title
    c.position.`type` @= PositionType.Relative
    c.overflow.y @= Overflow.Auto
    c
  }
  /** Shared app header (same for all example screens). */
  protected def header: Header = ExampleApp.appHeader

  def url: URL = URL.parse(s"https://github.com/outr/youi/tree/master/example/src/main/scala/io/youi/example/ui/${getClass.getSimpleName}.scala")

  override protected def init(): Task[Unit] = {
    scribe.info(s"Initializing screen! ${getClass.getSimpleName}")
    super.init().flatMap { _ =>
      ui.children -= container
      container.size.height := ui.size.height() - ExampleApp.appHeader.size.height()
      scribe.info("Screen initialized!")
      Task.unit
    }
  }

  override protected def activate(): Task[Unit] = {
    ExampleApp.contentContainer.children.clear()
    ExampleApp.contentContainer.children += container
    container.size.width := ExampleApp.contentContainer.size.width
    container.size.height := ExampleApp.contentContainer.size.height
    container.backgroundColor := ExampleApp.bgColor
    super.activate()
  }

  override protected def deactivate(): Task[Unit] = {
    ExampleApp.contentContainer.children -= container
    super.deactivate()
  }
}