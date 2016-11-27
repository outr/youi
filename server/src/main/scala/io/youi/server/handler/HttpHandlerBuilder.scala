package io.youi.server.handler

import io.youi.http.{Content, HttpConnection}
import io.youi.net.{URL, URLMatcher}
import io.youi.server.Server

case class HttpHandlerBuilder(server: Server,
                              urlMatcher: Option[URLMatcher] = None,
                              cachingManager: CachingManager = CachingManager.Default) {
  def matcher(urlMatchers: URLMatcher*): HttpHandlerBuilder = if (urlMatchers.isEmpty) {
    copy(urlMatcher = None)
  } else if (urlMatchers.tail.isEmpty) {
    copy(urlMatcher = Some(urlMatchers.head))
  } else {
    copy(urlMatcher = Some(URLMatcher.combined(urlMatchers: _*)))
  }

  def caching(cachingManager: CachingManager): HttpHandlerBuilder = copy(cachingManager = cachingManager)

  def resource(f: URL => Option[Content]): HttpHandler = {
    val handler: HttpHandler = (connection: HttpConnection) => {
      if (urlMatcher.forall(_.matches(connection.request.url))) {
        f(connection.request.url).foreach { content =>
          SenderHandler(content, caching = cachingManager).handle(connection)
        }
      }
    }
    server.handlers += handler
    handler
  }
}