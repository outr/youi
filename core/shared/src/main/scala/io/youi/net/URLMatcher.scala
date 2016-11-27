package io.youi.net

trait URLMatcher {
  def matches(url: URL): Boolean
}

object URLMatcher {
  def combined(matchers: URLMatcher*): URLMatcher = (url: URL) => matchers.forall(_.matches(url))

  object url {
    def exact(urlString: String): URLMatcher = (url: URL) => url.decoded == urlString
  }

  object path {
    def exact(path: String): URLMatcher = (url: URL) => url.path.decoded == path
  }
}