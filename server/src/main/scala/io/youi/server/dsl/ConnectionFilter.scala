package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.server.handler.HttpHandler

import scala.concurrent.Future

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

  override def handle(connection: HttpConnection): Future[HttpConnection] = Future.successful {
    filter(connection) match {
      case Some(c) => {
        val last = c.store.getOrElse[List[ConnectionFilter]](ConnectionFilter.LastKey, Nil)
        ConnectionFilter.recurse(c, last)
      }
      case None => connection
    }
  }
}

object ConnectionFilter {
  private val LastKey: String = "ConnectionFilterLast"

  def recurse(connection: HttpConnection, filters: List[ConnectionFilter]): HttpConnection = if (filters.isEmpty) {
    connection
  } else {
    val filter = filters.head
    filter.filter(connection) match {
      case None => connection
      case Some(modified) => recurse(modified, filters.tail)
    }
  }
}