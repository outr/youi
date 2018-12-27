package io.youi.client

import scala.concurrent.duration.FiniteDuration

object ClientPlatform {
  def createClient(config: HttpClientConfig): HttpClient = {
    JSHttpClient(config)
  }

  def createPool(maxIdleConnections: Int = ConnectionPool.maxIdleConnections,
                 keepAlive: FiniteDuration = ConnectionPool.keepAlive): ConnectionPool = {
    JSConnectionPool(maxIdleConnections, keepAlive)
  }
}
