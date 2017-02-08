package io.youi.template

import scala.concurrent.Future

import org.scalajs.dom._

trait ClientTemplateCommunication extends TemplateCommunication {
  override def reload(force: Boolean): Future[Unit] = Future.successful(window.location.reload(force))
}
