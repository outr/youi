package org.hyperscala.web.resource.handler

import org.hyperscala.web.Website

/**
 * ApplicationWebResourceManager can be mixed in to provide storage of WebResources in the application context.
 *
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ApplicationWebResourceManager extends WebResourceManager {
  override def sessionOption = Some(Website().application)
}
