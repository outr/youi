package org.hyperscala.web.resource.handler

/**
 * ClassLoaderWebResourceManager can be used to provide ClassLoader lookups of resources to be streamed back to the
 * client.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ClassLoaderWebResourceManager(classLoader: ClassLoader = getClass.getClassLoader) extends URLWebResourceManager {
  def lookup(uri: String) = classLoader.getResource(uri)
}