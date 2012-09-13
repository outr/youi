package org.hyperscala.web.resource.handler

import org.hyperscala.web.resource.WebResource
import org.powerscala.Priority
import org.powerscala.hierarchy.Element

/**
 * WebResourceHandler is the base class to deliver WebResources based on URI lookups.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResourceHandler extends Element {
  /**
   * Find the WebResource by the supplied URI or None if this handler does not match a resource.
   *
   * @param uri web resource URI being requested
   * @return Option[WebResource]
   */
  def apply(uri: String): Option[WebResource]

  /**
   * Sorting priority of the handler among other handlers in the site.
   *
   * Defaults to Priority.Normal
   */
  def priority: Priority = Priority.Normal
}