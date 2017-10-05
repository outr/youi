package io.youi.app.screen

import io.youi._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UIScreen extends Screen {
  protected def renderer: Renderer = Renderer

  protected def drawable: Drawable

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    renderer.drawable := drawable
    renderer.visible := true
  }

  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
    renderer.visible := false
  }
}