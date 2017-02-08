package io.youi.template

import io.youi.communication.{Communication, client}

import scala.concurrent.Future

trait TemplateCommunication extends Communication {
  @client def reload(force: Boolean): Future[Unit]
}
