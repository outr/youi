package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection
import io.youi.server.handler.HttpHandler

trait ConnectionFilter extends HttpHandler {
  def filter(connection: HttpConnection): IO[FilterResponse]

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

  override def handle(connection: HttpConnection): IO[HttpConnection] = {
    filter(connection).flatMap {
      case FilterResponse.Continue(c) => {
        val last = c.store.getOrElse[List[ConnectionFilter]](ConnectionFilter.LastKey, Nil)
        ConnectionFilter.recurse(c, last).map(_.connection)
      }
      case FilterResponse.Stop(c) => IO.pure(c)
    }
  }
}

object ConnectionFilter {
  private val LastKey: String = "ConnectionFilterLast"

  def recurse(connection: HttpConnection, filters: List[ConnectionFilter]): IO[FilterResponse] = if (filters.isEmpty) {
    IO.pure(FilterResponse.Continue(connection))
  } else {
    val filter = filters.head
    filter.filter(connection).flatMap {
      case stop: FilterResponse.Stop => IO.pure(stop)
      case FilterResponse.Continue(c) => recurse(c, filters.tail)
    }
  }
}