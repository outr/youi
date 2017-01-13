package io.youi

import java.io.File

import io.youi.net.{URL, URLMatcher}

import scala.language.implicitConversions

package object http {
  implicit def file2Content(file: File): Content = Content.file(file)
  implicit def url2Content(url: java.net.URL): Content = Content.url(url)

  def combined(matchers: URLMatcher*): URLMatcher = new URLMatcher {
    override def matches(url: URL): Boolean = matchers.forall(_.matches(url))
  }

  object url {
    def exact(urlString: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.decoded.toString == urlString
    }
  }

  object path {
    def exact(path: String): URLMatcher = new URLMatcher {
      override def matches(url: URL): Boolean = url.path.decoded == path
    }
  }

  object all extends URLMatcher {
    override def matches(url: URL): Boolean = true
  }
}