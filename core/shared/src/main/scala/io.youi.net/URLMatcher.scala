package io.youi.net

trait URLMatcher {
  def matches(url: URL): Boolean
}

object URLMatcher {
  def url(urlString: String): URLMatcher = new URLMatcher {
    override def matches(url: URL): Boolean = url.decoded == urlString
  }

  def path(path: String): URLMatcher = new URLMatcher {
    override def matches(url: URL): Boolean = url.path.decoded == path
  }
}