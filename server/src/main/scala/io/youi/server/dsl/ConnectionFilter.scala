package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.server.handler.HttpHandler

trait ConnectionFilter extends HttpHandler {
  def filter(connection: HttpConnection): Option[HttpConnection]

  def /(that: ConnectionFilter): ConnectionFilter = CombinedConnectionFilter(this, that)

  def /(filters: Seq[ConnectionFilter]): ConnectionFilter = this / ListConnectionFilter(filters.toList)

  /**
    * Adds a filter to apply at the very end, presuming this wasn't canceled.
    *
    * @param connection the connection to attach to
    * @param filters the filters to run last
    */
  def last(connection: HttpConnection, filters: ConnectionFilter*): Unit = {
    val current = connection.store.getOrElse[List[ConnectionFilter]](ConnectionFilter.LastKey, Nil)
    connection.store(ConnectionFilter.LastKey) = current ::: filters.toList
  }

  override def handle(connection: HttpConnection): Unit = filter(connection).map { connection =>
    val last = connection.store.getOrElse[List[ConnectionFilter]](ConnectionFilter.LastKey, Nil)
    last.toStream.flatMap(_.filter(connection)).headOption
  }
}

object ConnectionFilter {
  private val LastKey: String = "ConnectionFilterLast"
}