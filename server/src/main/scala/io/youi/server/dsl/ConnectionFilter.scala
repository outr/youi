package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.server.handler.HttpHandler
import scribe.Execution.global

import scala.concurrent.Future

trait ConnectionFilter extends HttpHandler {
  def filter(connection: HttpConnection): Future[FilterResponse]

  protected def continue(connection: HttpConnection): FilterResponse = FilterResponse.Continue(connection)
  protected def stop(connection: HttpConnection): FilterResponse = FilterResponse.Stop(connection)

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

  override def handle(connection: HttpConnection): Future[HttpConnection] = {
    filter(connection).flatMap {
      case FilterResponse.Continue(c) => {
        val last = c.store.getOrElse[List[ConnectionFilter]](ConnectionFilter.LastKey, Nil)
        ConnectionFilter.recurse(c, last).map(_.connection)
      }
      case FilterResponse.Stop(c) => Future.successful(c)
    }
  }
}

object ConnectionFilter {
  private val LastKey: String = "ConnectionFilterLast"

  def recurse(connection: HttpConnection, filters: List[ConnectionFilter]): Future[FilterResponse] = if (filters.isEmpty) {
    Future.successful(FilterResponse.Continue(connection))
  } else {
    val filter = filters.head
    filter.filter(connection).flatMap {
      case stop: FilterResponse.Stop => Future.successful(stop)
      case FilterResponse.Continue(c) => recurse(c, filters.tail)
    }
  }
}