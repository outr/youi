package io.youi.client

import java.util.concurrent.TimeUnit

import scala.concurrent.duration._

case class JVMConnectionPool(maxIdleConnections: Int, keepAlive: FiniteDuration) extends ConnectionPool {
  private[client] lazy val pool = new okhttp3.ConnectionPool(maxIdleConnections, keepAlive.toMillis, TimeUnit.MILLISECONDS)

  override def idle: Int = pool.idleConnectionCount()
  override def active: Int = pool.connectionCount() - pool.idleConnectionCount()
}