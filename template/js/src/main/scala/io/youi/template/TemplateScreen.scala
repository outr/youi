package io.youi.template

import io.youi.History
import io.youi.activate.ActivationSupport
import io.youi.app.screen.ContentScreen

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TemplateScreen(val path: String) extends ContentScreen with ActivationSupport {
  override protected def testing = true

  override protected def load(): Future[Unit] = super.load().map { _ =>
    History.fixAnchors()
  }

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    activation.activate()
  }

  override protected def deactivate(): Future[Unit] = Future.successful(activation.deactivate()).map(_ => super.deactivate())
}