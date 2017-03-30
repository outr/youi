package io.youi.app.screen

import io.youi.canvas.CanvasRenderer

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait CanvasScreen extends Screen {
  override protected def init(): Future[Unit] = super.init().flatMap(_ => CanvasRenderer.Loaded)
}