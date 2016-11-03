package io.youi.server.test

import io.youi.server.{Server, ServerImplementation}

class TestServer extends Server {
  override protected val implementation: ServerImplementation = TestServerImplementation
}
