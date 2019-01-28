package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.net.Path

import scala.concurrent.Future

case class PathFilter(path: Path) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = Future.successful {
    if (path == connection.request.url.path) {
      val args = path.extractArguments(connection.request.url.path)
      if (args.nonEmpty) {
        connection.store(PathFilter.Key) = args
      }
      FilterResponse.Continue(connection)
    } else {
      FilterResponse.Stop(connection)
    }
  }
}

object PathFilter {
  val Key: String = "pathArguments"

  def argumentsFromConnection(connection: HttpConnection): Map[String, String] = {
    connection.store.getOrElse[Map[String, String]](Key, Map.empty)
  }
}