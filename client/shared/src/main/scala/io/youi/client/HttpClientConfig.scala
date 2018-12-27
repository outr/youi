package io.youi.client

import java.nio.file.{Path, Paths}

import io.youi.client.intercept.Interceptor

import scala.concurrent.duration._

case class HttpClientConfig(retries: Int = 0,
                            retryDelay: FiniteDuration = 5.seconds,
                            interceptor: Interceptor = Interceptor.empty,
                            connectionPool: ConnectionPool = ConnectionPool.default,
                            saveDirectory: Path = Paths.get(System.getProperty("java.io.tmpdir")),
                            timeout: FiniteDuration = 15.seconds,
                            pingInterval: Option[FiniteDuration] = None,
                            dns: DNS = DNS.default,
                            dropNullValuesInJson: Boolean = false,
                            sessionManager: Option[SessionManager] = None) {
  def retries(retries: Int): HttpClientConfig = copy(retries = retries)
  def retryDelay(retryDelay: FiniteDuration): HttpClientConfig = copy(retryDelay = retryDelay)
  def interceptor(interceptor: Interceptor): HttpClientConfig = copy(interceptor = interceptor)
  def connectionPool(connectionPool: ConnectionPool): HttpClientConfig = copy(connectionPool = connectionPool)
  def saveDirectory(saveDirectory: Path): HttpClientConfig = copy(saveDirectory = saveDirectory)
  def timeout(timeout: FiniteDuration): HttpClientConfig = copy(timeout = timeout)
  def pingInterval(pingInterval: Option[FiniteDuration]): HttpClientConfig = copy(pingInterval = pingInterval)
  def dns(dns: DNS): HttpClientConfig = copy(dns = dns)
  def sessionManager(sessionManager: SessionManager): HttpClientConfig = copy(sessionManager = Some(sessionManager))
  def session(session: Session): HttpClientConfig = copy(sessionManager = Some(new SessionManager(session)))
}