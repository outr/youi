package io.youi.net

trait URLMatcher {
  def matches(url: URL): Boolean
}