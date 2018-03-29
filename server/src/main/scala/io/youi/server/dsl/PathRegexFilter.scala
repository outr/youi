package io.youi.server.dsl

import io.youi.http.HttpConnection

import scala.util.matching.Regex

case class PathRegexFilter(regex: Regex) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    val path = connection.request.url.path.decoded
    if (path.matches(regex.regex)) {
      Some(connection)
    } else {
      None
    }
  }
}