package io.youi.http

import io.youi.net.{URL, URLMatcher}
import io.youi.server.KeyStore
import scribe.Priority

trait ProxyHandler extends Ordered[ProxyHandler] {
  def priority: Priority = Priority.Normal

  def matches(url: URL): Boolean

  def proxy(url: URL): URL

  def keyStore: Option[KeyStore] = None

  override def compare(that: ProxyHandler): Int = Priority.PriorityOrdering.compare(this.priority, that.priority)
}

object ProxyHandler {
  def apply(matcher: URLMatcher, keyStore: Option[KeyStore] = None, priority: Priority = Priority.Normal)
           (redirect: URL => URL): ProxyHandler = new ProxyHandler {
    override def matches(url: URL): Boolean = matcher.matches(url)

    override def proxy(url: URL): URL = redirect(url)
  }
}