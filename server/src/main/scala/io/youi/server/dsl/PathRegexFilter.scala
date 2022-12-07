package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection

import scala.util.matching.Regex

case class PathRegexFilter(regex: Regex) extends ConnectionFilter {
  override def filter(connection: HttpConnection): IO[FilterResponse] = IO {
    val path = connection.request.url.path.decoded
    if (path.matches(regex.regex)) {
      FilterResponse.Continue(connection)
    } else {
      FilterResponse.Stop(connection)
    }
  }
}