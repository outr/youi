package io.youi.http

import io.youi.net.URL

trait ProxyHandler {
  def proxy(connection: HttpConnection): Option[URL]
}

object ProxyHandler {
  val key: String = "proxyHandler"
}