package io.youi.client

import io.youi.client.intercept.Interceptor

import scala.concurrent.duration.FiniteDuration

object ClientPlatform {
  def createClient(defaultRetries: Int = HttpClient.retries,
                   defaultRetryDelay: FiniteDuration = HttpClient.retryDelay,
                   defaultInterceptor: Interceptor = HttpClient.interceptor,
                   connectionPool: ConnectionPool = HttpClient.connectionPool): HttpClient = {
    JSHttpClient(
      defaultRetries = defaultRetries,
      defaultRetryDelay = defaultRetryDelay,
      defaultInterceptor = defaultInterceptor,
      connectionPool = connectionPool
    )
  }

  def createPool(maxIdleConnections: Int = ConnectionPool.maxIdleConnections,
                 keepAlive: FiniteDuration = ConnectionPool.keepAlive): ConnectionPool = {
    JSConnectionPool(maxIdleConnections, keepAlive)
  }
}
