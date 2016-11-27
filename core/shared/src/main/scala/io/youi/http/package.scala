package io.youi

import java.io.File

import io.youi.net.{URL, URLMatcher}

import scala.language.implicitConversions

package object http {
  implicit def file2Content(file: File): Content = Content.file(file)
  implicit def url2Content(url: java.net.URL): Content = Content.url(url)

  def combined(matchers: URLMatcher*): URLMatcher = (url: URL) => matchers.forall(_.matches(url))

  object url {
    def exact(urlString: String): URLMatcher = (url: URL) => url.decoded == urlString
  }

  object path {
    def exact(path: String): URLMatcher = (url: URL) => url.path.decoded == path
  }
}