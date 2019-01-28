package io.youi.server.dsl

import io.youi.http.HttpConnection

import scala.concurrent.Future
import scala.util.matching.Regex

case class PathRegexFilter(regex: Regex) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = Future.successful {
    val path = connection.request.url.path.decoded
    if (path.matches(regex.regex)) {
      FilterResponse.Continue(connection)
    } else {
      FilterResponse.Stop(connection)
    }
  }
}