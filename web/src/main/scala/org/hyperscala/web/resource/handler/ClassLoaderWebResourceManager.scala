package org.hyperscala.web.resource.handler


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class ClassLoaderWebResourceManager(classLoader: ClassLoader = getClass.getClassLoader) extends URLWebResourceManager {
  def lookup(uri: String) = classLoader.getResource(uri)
}