package youi

import spice.net.URL

object Cache {
  var implementation: CacheImplementation = scala.compiletime.uninitialized

  def cached(url: URL): String = {
    assert(implementation != null, "CacheImplementation must be set before Cache can be used.")
    implementation.cached(url)
  }
}

trait CacheImplementation {
  def cached(url: URL): String
}