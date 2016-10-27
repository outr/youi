package io.youi.http.server

import pl.metastack.metarx.Sub

class ServerConfig(server: Server) {
  /**
    * If set to true the server will automatically restart when any configuration option changes. If set to false
    * the server must be manually restarted before changes will take effect.
    *
    * Default is true.
    */
  val autoRestart: Sub[Boolean] = Sub(true)

  /**
    * The hostname or ip address to bind the server to. Set "0.0.0.0" for all addresses.
    *
    * Defaults to "127.0.0.1"
    */
  val host: Sub[String] = sub("127.0.0.1")

  /**
    * The port to bind the server to.
    *
    * Defaults to 8080
    */
  val port: Sub[Int] = sub(8080)

  protected def sub[T](value: T): Sub[T] = {
    val s = Sub[T](value)
    s.silentAttach { value =>
      if (autoRestart.get && server.isRunning) {
        server.restart()
      }
    }
    s
  }
}
