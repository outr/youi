package io.youi.app.screen

import io.youi._
import io.youi.component.Renderer
import io.youi.hypertext.Canvas

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait UIScreen extends Screen {
  def renderer: Renderer = UIScreen.renderer

  override protected def init(): Future[Unit] = super.init().flatMap(_ => Renderer.Loaded).map { _ =>
    UIScreen.renderer.size.width := ui.size.width
    UIScreen.renderer.size.height := ui.size.height
  }

  override protected def load(): Future[Unit] = super.load().map(_ => createUI())

  def createUI(): Unit

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    UIScreen.canvas.visible := true
  }

  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
    UIScreen.canvas.visible := false
  }
}

object UIScreen {
  lazy val canvas = new Canvas {
    ui.children += this
  }

  lazy val renderer = Renderer(canvas)
}