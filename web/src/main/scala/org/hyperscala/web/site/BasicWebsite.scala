package org.hyperscala.web.site

import org.hyperscala.web.session.MapSession

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BasicWebsite extends Website[MapSession] {
  protected def createSession() = new MapSession
}
