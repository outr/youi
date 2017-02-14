package io.youi.workflow

import scala.concurrent.Future

trait Task {
  def start(): Future[Unit]
}