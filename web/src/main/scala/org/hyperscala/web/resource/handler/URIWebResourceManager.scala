package org.hyperscala.web.resource.handler

/**
 * Specifies an explicit URI for matching to this manager.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
abstract class URIWebResourceManager(uri: String) extends WebResourceManager {
  def isMatch(uri: String) = this.uri == uri
}
