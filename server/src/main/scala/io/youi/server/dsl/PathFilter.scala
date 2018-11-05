package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.net.Path

case class PathFilter(path: Path) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    if (path == connection.request.url.path) {
      val args = path.extractArguments(connection.request.url.path)
      if (args.nonEmpty) {
        connection.store(PathFilter.Key) = args
      }
      Some(connection)
    } else {
      None
    }
  }
}

object PathFilter {
  val Key: String = "pathArguments"

  def argumentsFromConnection(connection: HttpConnection): Map[String, String] = {
    connection.store.getOrElse[Map[String, String]](Key, Map.empty)
  }
}