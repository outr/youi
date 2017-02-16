package io.youi.template

import io.youi.activate.ActivationSupport
import io.youi.app.screen.ContentScreen
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class TemplateScreen(val path: String) extends ContentScreen with ActivationSupport {
  override protected def testing = false

  override protected def load(): Future[Unit] = super.load().map { _ =>
    ClientTemplateApplication.screenFixes()
  }

  override protected def activate(): Future[Unit] = super.activate().map { _ =>
    println(s"Activating: $path")
    activation.activate()
  }

  override protected def deactivate(): Future[Unit] = Future.successful(activation.deactivate()).map(_ => super.deactivate())
}