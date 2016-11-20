package io.youi.example

import com.outr.scribe.Logging
import io.youi.http.{HttpConnection, ProxyHandler}
import io.youi.net.URL
import io.youi.server.handler.HttpHandler

object ProxyExample extends HttpHandler with ProxyHandler with Logging {
  override def handle(connection: HttpConnection): Unit = {
    connection.proxySupport = this
  }

  override def proxy(connection: HttpConnection): Option[URL] = {
    Some(URL("http://google.com").copy(path = connection.request.url.path))
  }
}
