package io.youi.server

class UndertowServer extends Server {
  override protected val implementation: UndertowServerImplementation = new UndertowServerImplementation(this)
}
