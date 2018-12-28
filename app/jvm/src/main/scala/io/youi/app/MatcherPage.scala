package io.youi.app

import io.youi.http.HttpConnection
import io.youi.http.content.Content
import io.youi.net.URLMatcher

trait MatcherPage extends Page {
  protected def matcher: URLMatcher

  protected def resource(httpConnection: HttpConnection): Option[Content]

  override protected def matches(connection: HttpConnection): Option[Content] = if (matcher.matches(connection.request.url)) {
    resource(connection)
  } else {
    None
  }
}