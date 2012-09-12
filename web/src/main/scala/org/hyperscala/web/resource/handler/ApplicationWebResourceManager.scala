package org.hyperscala.web.resource.handler

import org.hyperscala.web.Website

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ApplicationWebResourceManager extends WebResourceManager {
  override def sessionOption = Some(Website().application)
}
