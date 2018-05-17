package io.youi.client

import java.util.concurrent.TimeUnit

import scala.concurrent.duration._

case class ConnectionPool(maxIdleConnection: Int = 100, keepAliveDuration: FiniteDuration = 5.minutes) {
  private[client] lazy val pool = new okhttp3.ConnectionPool(maxIdleConnection, keepAliveDuration.toMillis, TimeUnit.MILLISECONDS)

  def idle: Int = pool.idleConnectionCount()
  def active: Int = pool.connectionCount() - pool.idleConnectionCount()
  def total: Int = pool.connectionCount()
}

object ConnectionPool {
  lazy val default: ConnectionPool = ConnectionPool()
}