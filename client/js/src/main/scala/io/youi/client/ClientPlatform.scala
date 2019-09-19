package io.youi.client

import scala.concurrent.duration.FiniteDuration

object ClientPlatform {
  def implementation(): HttpClientImplementation = new JSHttpClientImplementation(HttpClientConfig.default())


  def createPool(maxIdleConnections: Int = ConnectionPool.maxIdleConnections,
                 keepAlive: FiniteDuration = ConnectionPool.keepAlive): ConnectionPool = {
    JSConnectionPool(maxIdleConnections, keepAlive)
  }

  def defaultSaveDirectory: String = "/"
}
