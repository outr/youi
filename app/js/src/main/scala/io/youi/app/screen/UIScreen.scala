package io.youi.app.screen

import io.youi._
import io.youi.component.Container

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UIScreen extends Screen {
  lazy val container: Container = new Container

  override protected def init(): Future[Unit] = super.init().map { _ =>
    container.size.width := ui.width
    container.size.height := ui.height
    container.visible := false
    ui.renderer.children += container
  }

  override protected def load(): Future[Unit] = super.load().map(_ => createUI())

  def createUI(): Unit

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    ui.renderer.show()
    container.visible := true
  }

  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
    container.visible := false
    ui.renderer.hide()
  }
}