package io.youi.example

import com.outr.scribe.Logging
import io.youi.http.{HttpConnection, ProxyHandler}
import io.youi.net.URL
import io.youi.server.handler.HttpHandler

object ProxyExample extends ProxyHandler with Logging {
  override def proxy(connection: HttpConnection): Option[URL] = {
    Some(URL("http://google.com").copy(path = connection.request.url.path))
  }
}
