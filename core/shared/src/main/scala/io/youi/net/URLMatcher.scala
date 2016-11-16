package io.youi.net

trait URLMatcher {
  def matches(url: URL): Boolean
}

object URLMatcher {
  def combined(matchers: URLMatcher*): URLMatcher = new URLMatcher {
    override def matches(url: URL): Boolean = matchers.forall(_.matches(url))
  }

  object url {
    def exact(urlString: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.decoded == urlString
    }
  }

  object path {
    def exact(path: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.path.decoded == path
    }
  }
}