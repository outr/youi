package io.youi.app

import java.io.File

import io.youi.http.HttpConnection
import io.youi.net.URLMatcher

trait MatcherPage extends Page {
  protected def matcher: URLMatcher

  protected def resource(httpConnection: HttpConnection): Option[File]

  override protected def matches(connection: HttpConnection): Option[File] = if (matcher.matches(connection.request.url)) {
    resource(connection)
  } else {
    None
  }
}
