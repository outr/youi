package io.youi.client
import io.youi.ajax.AjaxManager

import scala.concurrent.duration.FiniteDuration

case class JSConnectionPool(maxIdleConnections: Int = ConnectionPool.maxIdleConnections,
                            keepAlive: FiniteDuration = ConnectionPool.keepAlive) extends ConnectionPool {
  lazy val manager = new AjaxManager(maxIdleConnections)

  override def idle: Int = 0
  override def active: Int = manager.queue + manager.running
}
