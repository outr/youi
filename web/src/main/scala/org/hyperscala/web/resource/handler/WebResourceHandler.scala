package org.hyperscala.web.resource.handler

import org.hyperscala.web.resource.WebResource
import org.powerscala.Priority

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait WebResourceHandler {
  def apply(uri: String): Option[WebResource]

  def priority: Priority = Priority.Normal
}