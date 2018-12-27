package io.youi.client

import scala.concurrent.duration.FiniteDuration

object ClientPlatform {
  def createClient(config: HttpClientConfig): HttpClient = {
    JVMHttpClient(config)
  }

  def createPool(maxIdleConnections: Int = ConnectionPool.maxIdleConnections,
                 keepAlive: FiniteDuration = ConnectionPool.keepAlive): ConnectionPool = {
    JVMConnectionPool(maxIdleConnections, keepAlive)
  }
}