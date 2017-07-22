package io.youi.server

trait ServerImplementationCreator {
  def create(server: Server): ServerImplementation
}
