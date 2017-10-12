package io.youi.app.screen

import io.youi.component.Container
import io.youi.drawable.Drawable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait UIScreen extends DrawableScreen {
  protected lazy val container: Container = new Container

  override protected def drawable: Future[Drawable] = createUI().map { _ =>
    container
  }

  def createUI(): Future[Unit]
}