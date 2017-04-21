package io.youi

import io.youi.net.URL

object Cache {
  var implementation: CacheImplementation = _

  def cached(url: URL): String = {
    assert(implementation != null, "CacheImplementation must be set before Cache can be used.")
    implementation.cached(url)
  }
}

trait CacheImplementation {
  def cached(url: URL): String
}