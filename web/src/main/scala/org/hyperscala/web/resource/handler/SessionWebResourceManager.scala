package org.hyperscala.web.resource.handler

import org.hyperscala.web.Website

/**
 * SessionWebResourceManager can be mixed in to provide storage of WebResources in the session context.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait SessionWebResourceManager extends WebResourceManager {
  override def sessionOption = Some(Website().session)
}
