package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.server.handler.HttpHandler

trait ConnectionFilter extends HttpHandler {
  def filter(connection: HttpConnection): Option[HttpConnection]

  def :>(that: ConnectionFilter): ConnectionFilter = CombinedConnectionFilter(this, that)

  def :>(filters: Seq[ConnectionFilter]): ConnectionFilter = this :> ListConnectionFilter(filters.toList)

  override def handle(connection: HttpConnection): Unit = filter(connection)
}