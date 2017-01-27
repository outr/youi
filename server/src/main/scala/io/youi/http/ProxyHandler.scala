package io.youi.http

import io.youi.net.URL
import io.youi.server.KeyStore

trait ProxyHandler {
  def proxy(connection: HttpConnection): Option[URL]

  def keyStore: Option[KeyStore] = None
}

object ProxyHandler {
  val key: String = "proxyHandler"
}