package io.youi.server

import com.outr.props._

class ServerConfig(server: Server) {
  /**
    * If set to true the server will automatically restart when any configuration option changes. If set to false
    * the server must be manually restarted before changes will take effect.
    *
    * Default is true.
    */
  val autoRestart: Var[Boolean] = Var(true)

  /**
    * The hostname or ip address to bind the server to. Set "0.0.0.0" for all addresses.
    *
    * Defaults to "127.0.0.1"
    */
  val host: Var[String] = prop("127.0.0.1")

  /**
    * The port to bind the server to.
    *
    * Defaults to 8080
    */
  val port: Var[Int] = prop(8080)

  protected def prop[T](value: T): Var[T] = {
    val v = Var[T](value)
    v.attach { value =>
      if (autoRestart.get && server.isRunning) {
        server.restart()
      }
    }
    v
  }
}
