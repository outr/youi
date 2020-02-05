package io.youi.stream

trait CacheBuilder {
  def isStale: Boolean
  def buildCache(): CachedInformation
}
