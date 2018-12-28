package io.youi

import io.youi.net.{Path, URL, URLMatcher}

import scala.language.implicitConversions

package object http {
  object combined {
    def all(matchers: URLMatcher*): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = matchers.forall(_.matches(url))
    }

    def any(matchers: URLMatcher*): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = matchers.exists(_.matches(url))
    }
  }

  object url {
    def exact(urlString: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.decoded.toString == urlString
    }
  }

  object host {
    def exact(host: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.host.equalsIgnoreCase(host)
    }
    def matches(regex: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.host.matches(regex)
    }
  }

  object path {
    def exact(path: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.path.decoded == path
    }
    def exact(path: Path): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.path == path
    }
    def matches(regex: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.path.decoded.matches(regex)
    }
    def startsWith(prefix: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.path.decoded.startsWith(prefix)
    }
    def endsWith(prefix: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.path.decoded.endsWith(prefix)
    }
  }

  object all extends URLMatcher {
    override def matches(url: URL): Boolean = true
  }
}