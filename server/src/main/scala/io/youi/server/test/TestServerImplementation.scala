package io.youi.server.test

import io.youi.server.ServerImplementation

object TestServerImplementation extends ServerImplementation {
  private var running = false

  override def start(): Unit = running = true

  override def stop(): Unit = running = false

  override def isRunning: Boolean = running
}
