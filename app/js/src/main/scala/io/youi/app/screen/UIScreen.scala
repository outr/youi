package io.youi.app.screen

import io.youi._
import io.youi.drawable.Drawable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UIScreen extends Screen {
  protected def renderer: Renderer = Renderer

  protected def drawable: Future[Drawable]

  override protected def activate(): Future[Unit] = super.activate().flatMap { _ =>
    drawable.map { d =>
      renderer.drawable.static(d)
      renderer.visible := true
    }
  }

  override protected def deactivate(): Future[Unit] = super.deactivate().map { _ =>
    renderer.visible := false
  }
}