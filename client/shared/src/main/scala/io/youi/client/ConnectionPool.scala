package io.youi.client

import scala.concurrent.duration._

trait ConnectionPool {
  def idle: Int
  def active: Int
  def total: Int
}

object ConnectionPool {
  var maxIdleConnections: Int = 100
  var keepAlive: FiniteDuration = 5.minutes

  lazy val default: ConnectionPool = apply()

  def apply(maxIdleConnections: Int = maxIdleConnections, keepAlive: FiniteDuration = keepAlive): ConnectionPool = {
    ClientPlatform.createPool(maxIdleConnections, keepAlive)
  }
}