package org.hyperscala.web.resource.handler

import org.hyperscala.web.resource.WebResource

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class RegistrationWebResourceManager extends WebResourceManager {
  private var resources = Map.empty[String, WebResource]

  def register(uri: String, resource: WebResource) = synchronized {
    resources += uri -> resource
  }

  def isMatch(uri: String) = resources.contains(uri)

  def create(uri: String) = resources.getOrElse(uri, null)
}
