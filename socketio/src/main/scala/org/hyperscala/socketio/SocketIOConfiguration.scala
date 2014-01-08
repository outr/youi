package org.hyperscala.socketio

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class SocketIOConfiguration(resource: String = "socket.io",
                                 connectTimeout: Long = 10000L,
                                 tryMultipleTransports: Boolean = true,
                                 reconnect: Boolean = true,
                                 reconnectionDelay: Long = 500L,
                                 reconnectionLimit: Int = Int.MaxValue,
                                 maxReconnectionAttempts: Int = 10,
                                 syncDisconnectOnUnload: Boolean = false,
                                 autoConnect: Boolean = true,
                                 flashPolicyPort: Int = 10843,
                                 forceNewConnection: Boolean = false) {
  def toJSON = {
    val d = SocketIO.defaults
    def o[T](name: String, value: T, default: T) = {
      if (value != default) {
        val v = value match {
          case null => "null"
          case s: String => s""""$s""""
          case _ => value.toString
        }
        Some(s"    '$name': $v")
      } else {
        None
      }
    }
    List(
      o("resource", resource, d.resource),
      o("connect timeout", connectTimeout, d.connectTimeout),
      o("try multiple transports", tryMultipleTransports, d.tryMultipleTransports),
      o("reconnect", reconnect, d.reconnect),
      o("reconnection delay", reconnectionDelay, d.reconnectionDelay),
      o("reconnection limit", reconnectionLimit, d.reconnectionLimit),
      o("max reconnection attempts", maxReconnectionAttempts, d.maxReconnectionAttempts),
      o("sync disconnect on unload", syncDisconnectOnUnload, d.syncDisconnectOnUnload),
      o("auto connect", autoConnect, d.autoConnect),
      o("flash policy port", flashPolicyPort, d.flashPolicyPort),
      o("force new connection", forceNewConnection, d.forceNewConnection)
    ).flatten.mkString("{", ", ", "}")
  }
}
