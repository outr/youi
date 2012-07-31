package org.hyperscala.site

import org.hyperscala.web.{PageHandler, Website}
import org.hyperscala.web.session.MapSession

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object HyperscalaSite extends Website[MapSession] {
  contents += PageHandler("about.html") {
    HyperscalaAbout
  }

  protected def createSession = new MapSession
}
