package io.youi.server.test

import io.youi.server.{Server, ServerImplementation, ServerImplementationCreator}

object TestServerImplementation extends ServerImplementation with ServerImplementationCreator {
  override def create(server: Server): ServerImplementation = this

  private var running = false

  override def start(): Unit = running = true

  override def stop(): Unit = running = false

  override def isRunning: Boolean = running
}