package io.youi.client

import scala.concurrent.duration.FiniteDuration

object ClientPlatform {
  def implementation(): HttpClientImplementation = new JVMHttpClientImplementation(HttpClientConfig.default())

  def createPool(maxIdleConnections: Int = ConnectionPool.maxIdleConnections,
                 keepAlive: FiniteDuration = ConnectionPool.keepAlive): ConnectionPool = {
    JVMConnectionPool(maxIdleConnections, keepAlive)
  }
}